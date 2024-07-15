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
    var status:Boolean,

    /**
     * Operation error code.
     */
    var errorCode:Int? = 0,
)
