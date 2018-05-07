package com.horical.kmvparchitect.data.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.horical.kmvparchitect.data.db.model.Blog

data class BlogResponse(
        @Expose
        @SerializedName("status_code")
        private var statusCode: String? = null,

        @Expose
        @SerializedName("message")
        private var message: String? = null,

        @Expose
        @SerializedName("data")
        var data: List<Blog>? = null)

