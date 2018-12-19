package com.aucc.game.rest.model

import com.google.gson.annotations.SerializedName

data class ListResponse<T>(@SerializedName("count") val count: Int,
                           @SerializedName("results") val results: List<T>)