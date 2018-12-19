package com.aucc.game.rest.model

import com.google.gson.annotations.SerializedName

data class Level(@SerializedName("id") val id: Int,
                 @SerializedName("title") val title: String,
                 @SerializedName("description") val description: String,
                 @SerializedName("question") val question: String,
                 @SerializedName("created_at") val createdAt: String,
                 @SerializedName("updated_at") val updatedAt: String,
                 @SerializedName("steps") val steps: List<Step>)