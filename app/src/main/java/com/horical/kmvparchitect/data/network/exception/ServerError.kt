package com.horical.kmvparchitect.data.network.exception

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ServerError(
        @Expose
        @SerializedName("msg")
        var message: String)
