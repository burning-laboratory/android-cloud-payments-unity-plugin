package com.burninglab.cpunityplugin.types.data

import kotlinx.serialization.Serializable

/**
 * Payer information data structure.
 */
@Serializable
data class PayerInfo(
    /**
     * Payer first name.
     */
    val firstName:String = "Player",

    /**
     * Payer last name.
     */
    val lastName:String = "Last Name",

    /**
     * Payer middle name.
     */
    val middleName:String = "Middle Name",

    /**
     * Payer birth day.
     */
    val birthDay:String = "Birth Day",

    /**
     * Payer address.
     */
    val address:String = "Address",

    /**
     * Payer street.
     */
    val street:String = "Street",

    /**
     * Payer city.
     */
    val city:String = "City",

    /**
     * Payer country.
     */
    val country:String = "Country",

    /**
     * Payer phone.
     */
    val phone:String = "Phone",

    /**
     * Payer post code.
     */
    val postCode:String = "Post code",

    /**
     * Payer account id.
     */
    val accountId:String = "Account ID",

    /**
     * Payer email.
     */
    val email:String = ""
)
