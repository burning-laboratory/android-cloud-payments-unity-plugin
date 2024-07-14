package com.burninglab.cpunityplugin.types.auth

import kotlinx.serialization.Serializable

/**
 * Authentication data for CloudPayments SDK.
 */
@Serializable
data class AuthData(
    /**
     * Cloud Payments merchant public id.
     */
    val publicId:String = "test_public_id"
)
