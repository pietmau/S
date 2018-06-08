package com.pppp.s.main.presenter

import com.pppp.s.main.model.CahedModel
import com.pppp.s.main.model.pokos.Movie
import com.pppp.s.main.view.MainView
import com.pppp.s.main.view.custom.MoviesFilter
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class MainPresenter(
    private val model: CahedModel,
    private val ioScheduler: Scheduler,
    private val mainThreadScheduler: Scheduler
) {
    private val compositeDisposable = CompositeDisposable()
    private var view: MainView? = null

    fun bind(view: MainView) {
        this.view = view
        val disposable =
            getMovies().subscribe({ movies -> onNewData(movies) }, { error -> onError(error) })
        compositeDisposable.add(disposable)
    }

    private fun onError(it: Throwable) {
        view?.onError(it)
    }

    fun onNewData(movies: List<Movie>) =
        view?.onNewData(movies)

    fun unbind() {
        view = null
        compositeDisposable.clear()
    }

    fun onQueryTextChange(query: String?): Boolean {
        val disposable = getMovies().map { MoviesFilter(it, this).filter(query) }//TODO UNSUBSCRIBE
            .subscribe({}, {})
        compositeDisposable.add(disposable)
        return true
    }

    private fun getMovies(): Observable<List<Movie>> {
        return model.subscribe()
            .map { list -> list.sorted() }
            .subscribeOn(ioScheduler)
            .observeOn(mainThreadScheduler)
    }
}