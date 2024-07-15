package com.burninglab.cpunityplugin.types

import kotlinx.serialization.Serializable

/**
 * Payment operation response.
 */
@Serializable
data class PaymentResponse(
    /**
     * Payment operation status.
     * True - Operation successfully complete.
     * False - Operation error.
     */
    val status:Boolean,

    /**
     * Operation error code.
     */
    val errorCode:Int = 0,

    /**
     * Operation error description.
     */
    val errorDescription:String = "Empty message"
)
