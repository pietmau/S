package com.pppp.s.main.presenter

import com.pppp.s.main.model.CachedModel
import com.pppp.s.main.model.pokos.Movie
import com.pppp.s.main.view.MainView
import com.pppp.s.plusAssign
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class MainPresenter(
    private val model: CachedModel,
    private val ioScheduler: Scheduler,
    private val mainThreadScheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    private val filterer: Filterer = FiltererImpl()
) {

    private var view: MainView? = null

    fun bind(view: MainView) {
        this.view = view
        subscribe()
    }

    private fun subscribe() {
        val disposable =
            getMovies().subscribe({ movies -> onNewData(movies) }, { error -> onError(error) })
        compositeDisposable.add(disposable)
    }

    private fun onError(it: Throwable) {
        stopRefreshing()
        view?.onError(it)
    }

    fun onNewData(movies: List<Movie>) {
        stopRefreshing()
        view?.onNewData(movies)
    }

    fun unbind() {
        view = null
        compositeDisposable.clear()
    }

    fun onQueryTextChange(query: String?): Boolean {
        compositeDisposable += getMovies()
            .map { movies -> filterer.filter(movies, query, this) }
            .subscribe({}, {})
        return true
    }

    private fun getMovies(): Observable<List<Movie>> {
        return model.subscribe()
            .map { list -> list.sorted() }
            .subscribeOn(ioScheduler)
            .observeOn(mainThreadScheduler)
    }

    fun refresh() {// It is not a real force refresh
        subscribe()
    }

    fun stopRefreshing() {
        view?.stopRefreshing()
    }
}
