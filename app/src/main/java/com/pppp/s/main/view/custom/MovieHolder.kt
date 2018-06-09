package com.pppp.s.main.view.custom

import android.support.v7.widget.RecyclerView
import android.view.View
import com.pppp.s.loadImage
import com.pppp.s.main.model.pokos.Movie
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindMovie(movie: Movie) {
        itemView.apply {
            image.loadImage(movie.poster)
            genre.text = movie.genre
            title.text = movie.title
        }
    }
}
