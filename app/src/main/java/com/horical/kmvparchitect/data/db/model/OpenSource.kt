package com.horical.kmvparchitect.data.db.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OpenSource(
        @Expose
        @SerializedName("project_url")
        var projectUrl: String? = null,

        @Expose
        @SerializedName("img_url")
        var imgUrl: String? = null,

        @Expose
        @SerializedName("title")
        var title: String? = null,

        @Expose
        @SerializedName("description")
        var description: String? = null)
