package com.aucc.game.ui.game

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.aucc.game.rest.RestRepository
import com.aucc.game.rest.model.Level
import com.aucc.game.rest.model.StatusResponse
import com.aucc.game.util.PrefUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GameViewModel @Inject constructor(private val prefUtils: PrefUtils,
                                        private val restRepository: RestRepository) : ViewModel() {

    private var disposable: CompositeDisposable = CompositeDisposable()

    val level = MutableLiveData<Level>()
    val processing = MutableLiveData<Boolean>()
    val status = MutableLiveData<StatusResponse>()
    val error = MutableLiveData<String>()

    fun initialize(level: Level) {
        this.level.value = level
        processing.value = null
        status.value = null
        error.value = null
    }

    fun checkAnswer(stepId: Int, answer: String) {
        processing.value = true
        disposable.add(restRepository.checkAnswer(stepId, answer).subscribeOn(
            Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(object: DisposableSingleObserver<StatusResponse>() {
                override fun onSuccess(t: StatusResponse) {
                    status.postValue(t)
                    processing.postValue(false)
                }

                override fun onError(e: Throwable) {
                    error.postValue(e.message)
                    processing.postValue(false)
                }
            }))
    }

    fun setLevelCompleted(level: Level) {
        prefUtils.setLevelCompleted(level.id)
    }
}