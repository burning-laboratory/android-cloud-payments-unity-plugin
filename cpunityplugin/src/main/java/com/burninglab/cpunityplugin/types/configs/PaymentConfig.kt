package com.burninglab.cpunityplugin.types.configs

import kotlinx.serialization.Serializable

/**
 * Cloud payments payment operation configuration.
 */
@Serializable
data class PaymentConfig(
    /**
     * Required email parameter.
     */
    val requiredEmail:Boolean = true,

    /**
     * User dual message payment.
     * Set true if you want use double operation configuration.
     */
    val useDualMessagePayment:Boolean = false,

    /**
     * Payment API server url.
     */
    val apiUrl:String = "https://api.cloudpayments.ru"
)
