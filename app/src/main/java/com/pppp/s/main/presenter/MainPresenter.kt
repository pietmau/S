package com.pppp.s.main.presenter

import com.pppp.s.main.model.MainModel
import com.pppp.s.main.model.pokos.Movie
import com.pppp.s.main.view.MainView
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class MainPresenter(
    private val model: MainModel,
    private val ioScheduler: Scheduler,
    private val mainThreadScheduler: Scheduler
) {
    private val compositeDisposable = CompositeDisposable()

    private var view: MainView? = null

    fun bind(view: MainView) {
        this.view = view
        compositeDisposable.add(
            model.subscribe()
                .subscribeOn(ioScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe({ movies ->
                    onMoviesReceived(movies)
                }, { error ->
                    onError(error)
                })
        )
    }

    private fun onError(it: Throwable?) {
        view?.onError(it)
    }

    private fun onMoviesReceived(movies: List<Movie>) =
        view?.onMoviesAvailable(movies)

    fun unbind() {
        view = null
        compositeDisposable.clear()
    }
}