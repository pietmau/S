package com.pppp.s.main.view.custom

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pppp.s.R
import com.pppp.s.main.model.pokos.Movie

class MoviesAdapter : RecyclerView.Adapter<MovieHolder>() {
    var movies: List<Movie>? = null

    override fun getItemCount(): Int = movies?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) =
        holder.bindMovie(movies?.get(position))
}
