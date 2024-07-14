package com.burninglab.cpunityplugin.types.data

import kotlinx.serialization.Serializable

/**
 * Payment invoice info data structure.
 */
@Serializable
data class InvoiceInfo(
    /**
     * Custom invoice operation identifier.
     */
    val invoiceId:String = "test_invoice_id",

    /**
     * Invoice amount.
     */
    val amount:Float = 10.0f,

    /**
     * Invoice amount currency code.
     */
    val currencyCode:String = "RUB",

    /**
     * Invoice description.
     */
    val invoiceDescription:String = "Test invoice description",
)
