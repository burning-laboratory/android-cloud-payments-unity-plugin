package com.burninglab.cloudpaymentsunityplugin

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.burninglab.cloudpaymentsunityplugin.ui.theme.CloudpaymentsunitypluginTheme
import com.burninglab.cpunityplugin.CloudPaymentsUnityPluginActivity
import com.burninglab.cpunityplugin.types.PaymentRequest
import com.burninglab.cpunityplugin.types.auth.AuthData
import com.burninglab.cpunityplugin.types.configs.PaymentConfig
import com.burninglab.cpunityplugin.types.configs.ResponseConfig
import com.burninglab.cpunityplugin.types.data.InvoiceInfo
import com.burninglab.cpunityplugin.types.data.PayerInfo
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainactivity)

        val button: Button = findViewById(R.id.start_payment_button)
        button.setOnClickListener { runPayment() }
    }

    private fun runPayment(){
        val paymentRequest = PaymentRequest(
            authData = AuthData(
                publicId = "pk_2092fac1c28490f3c7a035ad26c39"
            ),
            config = PaymentConfig(
                requiredEmail = true,
                useDualMessagePayment = false,
                apiUrl = "https://api.cloudpayments.ru"
            ),
            invoiceInfo = InvoiceInfo(
                invoiceId = "test_invoice",
                invoiceDescription = "Test invoice description",
                amount = 100.0f,
                currencyCode = "RUB"
            ),
            payerInfo = PayerInfo(
                firstName = "Payer first name",
                lastName = "Payer last name",
                middleName = "Payer middle name",
                birthDay = "01.01.2000",
                address = "Payer address. Example: 24486 Yukon Rd, Kasilof, Alaska 99610, USA",
                street = "Payer street. Example: 24486 Yukon Rd",
                city = "Kasilof",
                country = "USA",
                phone = "Payer mobile phone number. Example: +0 (907) 262-6261",
                postCode = "Payer adress post code. Example: 99610",
                accountId = "payer_account_unique_id",
                email = "support@burning-lab.com"
            ),
            responseConfig = ResponseConfig(
                callbackObjectName = "Unity Callback Object Name",
                callbackMethodName = "Unity Callback Method Name"
            )
        )

        val serializedPaymentRequest = Json.encodeToString(paymentRequest)

        val act = CloudPaymentsUnityPluginActivity()
        act.startPaymentWithoutNotify(this, serializedPaymentRequest)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CloudpaymentsunitypluginTheme {
        Greeting("Android")
    }
}