package com.burninglab.cloudpaymentsunityplugin

import android.os.Bundle
import android.util.JsonReader
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.burninglab.cloudpaymentsunityplugin.ui.theme.CloudpaymentsunitypluginTheme
import com.burninglab.cpunityplugin.CloudPaymentsUnityPluginActivity
import com.burninglab.cpunityplugin.types.PaymentRequest
import com.burninglab.cpunityplugin.types.auth.AuthData
import com.burninglab.cpunityplugin.types.configs.PaymentConfig
import com.burninglab.cpunityplugin.types.data.InvoiceInfo
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
            config = PaymentConfig(),
            invoiceInfo = InvoiceInfo()
        )

        val serializedPaymentRequest = Json.encodeToString(paymentRequest)

        val act = CloudPaymentsUnityPluginActivity()
        act.startPayment(this, serializedPaymentRequest)
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