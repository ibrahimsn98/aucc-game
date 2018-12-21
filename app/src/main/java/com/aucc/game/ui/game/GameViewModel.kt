package com.aucc.game.ui.game

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.aucc.game.rest.RestRepository
import com.aucc.game.rest.model.Level
import com.aucc.game.rest.model.StatusResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GameViewModel @Inject constructor(private val restRepository: RestRepository) : ViewModel() {

    private var disposable: CompositeDisposable = CompositeDisposable()

    val level = MutableLiveData<Level>()
    val status = MutableLiveData<StatusResponse>()

    fun checkAnswer(id: Int, answer: String) {
        disposable.add(restRepository.checkAnswer(id, answer).subscribeOn(
            Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object: DisposableSingleObserver<StatusResponse>() {
                override fun onSuccess(t: StatusResponse) {
                    status.postValue(t)
                }

                override fun onError(e: Throwable) {

                }
            }))
    }

    fun setLevelCompleted(level: Level) {
        //questRepository.insert(Quest(level.id, level.title, System.currentTimeMillis()/1000))
    }

    fun isLevelCompleted(level: Level): Boolean {
        //return questRepository.isExists(level.id)
        return false
    }
}