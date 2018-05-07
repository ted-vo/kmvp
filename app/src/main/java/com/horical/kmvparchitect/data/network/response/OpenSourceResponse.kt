package com.horical.kmvparchitect.data.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.horical.kmvparchitect.data.db.model.OpenSource

data class OpenSourceResponse(
        @Expose
        @SerializedName("status_code")
        private var statusCode: String? = null,

        @Expose
        @SerializedName("message")
        private var message: String? = null,

        @Expose
        @SerializedName("dataΩ")
        var data: List<OpenSource>? = null)