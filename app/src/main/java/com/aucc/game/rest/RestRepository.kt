package com.aucc.game.rest

import com.aucc.game.rest.model.Level
import com.aucc.game.rest.model.ListResponse
import com.aucc.game.rest.model.StatusResponse
import io.reactivex.Single
import javax.inject.Inject

class RestRepository @Inject constructor(private val restService: RestService) {

    fun getLevels(page: Int, perPage: Int): Single<ListResponse<Level>> {
        return restService.getLevels(page, perPage)
    }

    fun checkAnswer(stepId: Int, answer: String): Single<StatusResponse> {
        return restService.checkAnswer(stepId, answer)
    }
}