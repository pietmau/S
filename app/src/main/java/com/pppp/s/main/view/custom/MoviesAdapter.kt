package com.pppp.s.main.view.custom

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.pppp.s.R
import com.pppp.s.main.model.pokos.Movie
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject


class MoviesAdapter : RecyclerView.Adapter<MovieHolder>(), Filterable {
    private val filter = MoviesFilter(this)
    private val subject = PublishSubject.create<List<Movie>>()
    private val disposable = CompositeDisposable()
    var movies: List<Movie> = emptyList()
        set(value) {
            field = value.toList().sorted()// We make a copy
            onMoviesFiltered(movies)
        }

    init {
        start()
    }

    fun start() {
        val subscribe = subject
            .subscribeOn(Schedulers.io())
            .switchMap { data ->
                calculateDiff(data)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ pair ->
                pair.second.dispatchUpdatesTo(this)
                this.publishedMovies.clear()
                this.publishedMovies.addAll(pair.first)
            }, {

            })
        disposable.add(subscribe)//TODO unsubscribe
    }

    private fun calculateDiff(data: List<Movie>): Observable<Pair<List<Movie>, DiffUtil.DiffResult>>? {
        return Observable
            .just(
                Pair(data, DiffUtil.calculateDiff(MoviesDiffUtilCallback(publishedMovies, data)))
            )
    }

    var publishedMovies: MutableList<Movie> = mutableListOf()

    override fun getItemCount(): Int = publishedMovies?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) =
        holder.bindMovie(publishedMovies?.get(position))

    fun onQueryTextChange(query: String?) = filter.filter(query)

    override fun getFilter(): Filter = filter

    fun onMoviesFiltered(newData: List<Movie>) {
        subject.onNext(newData)
    }

}
