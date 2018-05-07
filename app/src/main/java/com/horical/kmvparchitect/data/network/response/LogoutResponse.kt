package com.horical.kmvparchitect.data.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LogoutResponse internal constructor(
        @Expose
        @SerializedName("status_code")
        var statusCode: String? = null,

        @Expose
        @SerializedName("message")
        var message: String? = null)