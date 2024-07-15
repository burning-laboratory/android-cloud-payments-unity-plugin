package com.burninglab.cpunityplugin

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.burninglab.cpunityplugin.types.PaymentRequest
import kotlinx.serialization.json.Json
import ru.cloudpayments.sdk.api.models.PaymentDataPayer
import ru.cloudpayments.sdk.configuration.CloudpaymentsSDK
import ru.cloudpayments.sdk.configuration.PaymentConfiguration
import ru.cloudpayments.sdk.configuration.PaymentData

class CloudPaymentsUnityPluginActivity : AppCompatActivity() {

    //region Private Fields

    /**
     * Activity extra payment request key.
     */
    private var PaymentRequestExtraKey = "payment_request"

    //endregion

    //region Activity Launchers

    /**
     * Launcher for cloud payments SDK activity.
     */
    val cpSdkLauncher = CloudpaymentsSDK.getInstance().launcher(this, result = {


        if (it.status != null) {
            if (it.status == CloudpaymentsSDK.TransactionStatus.Succeeded) {
                Toast.makeText(this, "Успешно! Транзакция №${it.transactionId}", Toast.LENGTH_SHORT).show()


            } else {
                if (it.reasonCode != 0) {
                    Toast.makeText(this, "Ошибка! Транзакция №${it.transactionId}. Код ошибки ${it.reasonCode}", Toast.LENGTH_SHORT).show()


                } else {
                    Toast.makeText(this, "Ошибка! Транзакция №${it.transactionId}.", Toast.LENGTH_SHORT).show()


                }
            }
        }

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
        var paymentRequest:PaymentRequest = Json.decodeFromString(serializedPaymentRequest.toString())

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
        intent.putExtra(PaymentRequestExtraKey, serializedPaymentRequest)
        unityPlayerActivity.startActivity(intent)
    }

    //endregion
}