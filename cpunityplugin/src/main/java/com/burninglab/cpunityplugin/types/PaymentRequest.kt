package com.burninglab.cpunityplugin.types

import com.burninglab.cpunityplugin.types.auth.AuthData
import com.burninglab.cpunityplugin.types.configs.PaymentConfig
import com.burninglab.cpunityplugin.types.data.InvoiceInfo
import com.burninglab.cpunityplugin.types.data.PayerInfo
import kotlinx.serialization.Serializable

/**
 * Cloud payments payment request data structure.
 */
@Serializable
data class PaymentRequest(
    /**
     * Cloud payments authentication data.
     */
    var authData:AuthData = AuthData(),

    /**
     * Cloud payments payer info.
     */
    val payerInfo: PayerInfo = PayerInfo(),

    /**
     * Invoice info data.
     */
    val invoiceInfo:InvoiceInfo = InvoiceInfo(),

    /**
     * Payment operation configuration.
     */
    val config:PaymentConfig = PaymentConfig(),

    /**
     * Custom JSON data.
     */
    val jsonData:String = ""
)
