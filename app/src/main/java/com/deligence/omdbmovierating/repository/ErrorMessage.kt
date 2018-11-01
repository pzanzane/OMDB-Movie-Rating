package com.clevertrap.basicsetupkotlin.retrofit

import com.google.gson.annotations.SerializedName

/**
 * Created by fidel25 on 12/05/2017.
 */

class ErrorMessage {

    @SerializedName("Error")
    var message: String? = null

    constructor() {}
    constructor(msg: String) {
        message = msg
    }

    companion object {

        private val BLANK_RESPONSE_MESSAGE = "Blank Response Body"

        private var errorMessage: ErrorMessage? = null
        val blankError: ErrorMessage
            get() {
                if (errorMessage == null) {
                    errorMessage = ErrorMessage(BLANK_RESPONSE_MESSAGE)
                }
                return errorMessage!!
            }
    }
}
