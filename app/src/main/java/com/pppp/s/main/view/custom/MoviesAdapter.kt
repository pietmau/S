package com.pppp.s.main.view.custom

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pppp.s.R
import com.pppp.s.main.model.pokos.Movie
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class MoviesAdapter : RecyclerView.Adapter<MovieHolder>() {
    private var movies: MutableList<Movie> = mutableListOf()
    private val subject = PublishSubject.create<List<Movie>>()
    private val disposable = CompositeDisposable()

    fun bind() {
        val subscribe = subject
            .subscribeOn(Schedulers.io())
            .switchMap { data -> calculateDiff(data) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ pair ->
                pair.second.dispatchUpdatesTo(this)
                movies.clear()
                movies.addAll(pair.first)
            }, { })
        disposable.add(subscribe)
    }

    fun unbind() {
        disposable.clear()
    }

    private fun calculateDiff(data: List<Movie>) =
        Observable.just(Pair(data, DiffUtil.calculateDiff(MoviesDiffUtilCallback(movies, data))))

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bindMovie(movies.get(position))
    }

    fun onNewData(newData: List<Movie>) {
        subject.onNext(newData)
    }
}
