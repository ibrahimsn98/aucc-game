package com.aucc.game.rest

import com.aucc.game.rest.model.Level
import com.aucc.game.rest.model.ListResponse
import com.aucc.game.rest.model.StatusResponse
import io.reactivex.Single
import retrofit2.http.*

interface RestService {

    @GET("levels/levels/")
    fun getLevels(@Query("page") page: Int, @Query("per_page") perPage: Int): Single<ListResponse<Level>>

    @FormUrlEncoded
    @POST("levels/check-answer/")
    fun checkAnswer(@Field("step") stepId: Int, @Field("answer") answer: String): Single<StatusResponse>
}