package com.pppp.s.main.view.custom

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.pppp.s.R
import com.pppp.s.main.model.pokos.Movie



class MoviesAdapter : RecyclerView.Adapter<MovieHolder>(), Filterable {
    private val filter: Filter = MoviesFilter(this)
    var movies: List<Movie> = emptyList()
        set(value) {
            field = value.toList()// We make a copy
            onMoviesFiltered(movies)
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
        val diffResult = DiffUtil.calculateDiff(MoviesDiffUtilCallback(newData, publishedMovies))
        diffResult.dispatchUpdatesTo(this)
        this.publishedMovies.clear()
        this.publishedMovies.addAll(newData)
    }

}
