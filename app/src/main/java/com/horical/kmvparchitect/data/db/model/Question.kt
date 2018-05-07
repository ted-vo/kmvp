package com.horical.kmvparchitect.data.db.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "questions")
data class Question(
        @Expose
        @PrimaryKey
        var id: Long,

        @Expose
        @SerializedName("question_text")
        @ColumnInfo(name = "question_text")
        var questionText: String,

        @Expose
        @SerializedName("question_img_url")
        @ColumnInfo(name = "question_img_url")
        var questionImgUrl: String?,

        @Expose
        @SerializedName("created_at")
        @ColumnInfo(name = "created_at")
        var createdAt: String?,

        @Expose
        @SerializedName("update_at")
        @ColumnInfo(name = "update_at")
        var updateAt: String?)