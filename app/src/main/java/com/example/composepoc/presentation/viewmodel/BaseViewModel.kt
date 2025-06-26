package com.example.composepoc.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.annotation.CallSuper
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

/** * Base ViewModel * - handle Rx jobs with launch() and clear them on onCleared */
abstract class BaseViewModel : ViewModel() {
    val disposables = CompositeDisposable()
    inline fun launchJob(job: () -> Disposable) {
        disposables.add(job())
    }

    fun launchJobs(vararg jobs: Disposable) {
        disposables.addAll(*jobs)
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}