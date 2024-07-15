package com.burninglab.cpunityplugin

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.burninglab.cpunityplugin.types.PaymentRequest
import com.burninglab.cpunityplugin.types.PaymentResponse
import com.unity3d.player.UnityPlayer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.cloudpayments.sdk.api.models.PaymentDataPayer
import ru.cloudpayments.sdk.configuration.CloudpaymentsSDK
import ru.cloudpayments.sdk.configuration.PaymentConfiguration
import ru.cloudpayments.sdk.configuration.PaymentData
import kotlin.math.log

class CloudPaymentsUnityPluginActivity : AppCompatActivity() {

    //region Private Fields

    /**
     * Activity extra payment request key.
     */
    private var PaymentRequestExtraKey = "payment_request"

    /**
     * Activity extra call send unity message key.
     */
    private var SendUnityMessageExtraKey = "send_unity_message";

    //endregion

    //region Activity Launchers

    /**
     * Launcher for cloud payments SDK activity.
     */
    val cpSdkLauncher = CloudpaymentsSDK.getInstance().launcher(this, result = {

        val response:PaymentResponse = PaymentResponse(
            status = false
        )

        if (it.status != null) {
            if (it.status == CloudpaymentsSDK.TransactionStatus.Succeeded) {
                response.status = true;
            } else {
                response.errorCode = it.reasonCode
            }
        }

        val extras = intent.extras;
        val needSendMessage = extras?.getBoolean(SendUnityMessageExtraKey)
        if (needSendMessage != null && needSendMessage){
            val serializedPaymentRequest:String? = extras.getString(PaymentRequestExtraKey)
            var paymentRequest:PaymentRequest = Json.decodeFromString(serializedPaymentRequest.toString())

            var objectName = paymentRequest.responseConfig.callbackObjectName;
            var methodName = paymentRequest.responseConfig.callbackMethodName;
            var serializedResponse:String = Json.encodeToString(response)

            UnityPlayer.UnitySendMessage(objectName, methodName, serializedResponse)
        }

        response.errorCode = 25;
        var serializedResponse:String = Json.encodeToString(response)
        Log.i("INFO", "Serialized payment response: $serializedResponse")

        finish()
    })

    //endregion

    //region Activity Event Methods

    /**
     * Handle an activity start method.
     */
    override fun onStart() {
        super.onStart()

        val extras = intent.extras;
        val serializedPaymentRequest:String? = extras?.getString(PaymentRequestExtraKey)
        val paymentRequest:PaymentRequest = Json.decodeFromString(serializedPaymentRequest.toString())

        startPayment(paymentRequest)
    }

    //endregion

    //region Private Methods

    /**
     * Start payment with cloud payments SDK method.
     */
    private fun startPayment(request: PaymentRequest){

        val payerInfo = PaymentDataPayer(
            firstName = request.payerInfo.firstName,
            lastName = request.payerInfo.lastName,
            middleName = request.payerInfo.middleName,
            birthDay = request.payerInfo.birthDay,
            address = request.payerInfo.address,
            city = request.payerInfo.city,
            country = request.payerInfo.country,
            phone = request.payerInfo.phone,
            postcode = request.payerInfo.postCode
        )

        val paymentData = PaymentData(
            amount = request.invoiceInfo.amount.toString(),
            currency = request.invoiceInfo.currencyCode,
            invoiceId = request.invoiceInfo.invoiceId,
            description = request.invoiceInfo.invoiceDescription,
            accountId = request.payerInfo.accountId,
            payer = payerInfo,
            email = request.payerInfo.email,
            jsonData = request.jsonData
        )

        val configuration = PaymentConfiguration(
            publicId = request.authData.publicId,
            paymentData = paymentData,
            requireEmail = request.config.requiredEmail,
            useDualMessagePayment = request.config.useDualMessagePayment,
            apiUrl = request.config.apiUrl
        )

        cpSdkLauncher.launch(configuration)
    }

    //endregion

    //region Public Methods

    /**
     * Start payment process.
     * This method adaptive for colling from unity.
     */
    public fun startPayment(unityPlayerActivity: Activity, serializedPaymentRequest: String){
        val intent = Intent(unityPlayerActivity, CloudPaymentsUnityPluginActivity::class.java)
        intent.putExtra(SendUnityMessageExtraKey, true)
        intent.putExtra(PaymentRequestExtraKey, serializedPaymentRequest)
        unityPlayerActivity.startActivity(intent)
    }

    /**
     * Start payment process.
     * This method does not calling unity callback.
     */
    public fun startPaymentWithoutNotify(unityPlayerActivity: Activity, serializedPaymentRequest: String){
        val intent = Intent(unityPlayerActivity, CloudPaymentsUnityPluginActivity::class.java)
        intent.putExtra(SendUnityMessageExtraKey, false)
        intent.putExtra(PaymentRequestExtraKey, serializedPaymentRequest)
        unityPlayerActivity.startActivity(intent)
    }

    //endregion
}