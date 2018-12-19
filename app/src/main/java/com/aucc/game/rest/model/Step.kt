package com.aucc.game.rest.model

import com.google.gson.annotations.SerializedName

data class Step(@SerializedName("id") val id: Int,
                @SerializedName("completed_message") val completedMessage: String,
                @SerializedName("right_response") val rightResponse: String)