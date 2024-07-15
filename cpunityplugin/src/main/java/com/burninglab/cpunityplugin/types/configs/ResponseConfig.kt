package com.burninglab.cpunityplugin.types.configs

import kotlinx.serialization.Serializable

/**
 * Unity player response configuration.
 */
@Serializable
data class ResponseConfig(
    /**
     * Unity object name for send message.
     */
    var callbackObjectName: String = "",

    /**
     * Unity method name for send message.
     */
    var callbackMethodName: String = ""
)
