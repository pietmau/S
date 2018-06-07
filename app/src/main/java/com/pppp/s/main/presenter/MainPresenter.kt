package com.pppp.s.main.presenter

import com.pppp.s.main.model.MainModel
import com.pppp.s.main.view.MainView
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class MainPresenter(
    private val model: MainModel,
    val ioScheduler: Scheduler,
    val mainThreadScheduler: Scheduler
) {
    private val compositeDisposable = CompositeDisposable()

    fun bind(view: MainView) {
        compositeDisposable.add(
            model.subscribe()
                .subscribeOn(ioScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe({
                    view.onMoviesAvvailable(it)
                }, {
                    view.onError(it)
                })
        )
    }

    fun unbind() = compositeDisposable.clear()
}