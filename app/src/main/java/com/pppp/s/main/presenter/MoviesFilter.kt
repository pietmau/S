package com.pppp.s.main.presenter

import android.widget.Filter
import com.pppp.s.main.model.pokos.Movie

class MoviesFilter(
    private val data: List<Movie>,
    private val presenter: MainPresenter
) : Filter() {

    override fun performFiltering(query: CharSequence?): FilterResults {
        val movies = mutableListOf<Movie>()
        if (query != null) {
            movies.addAll(data.filter { movie -> titleOrGenreContainsQuery(movie, query) })
        } else {
            movies.addAll(data)
        }
        return FilterResults().apply {
            count = movies.size
            values = movies
        }
    }

    override fun publishResults(query: CharSequence?, filteredResults: FilterResults?) {
        (filteredResults?.values as? List<Movie>)?.let { movies -> presenter.onNewData(movies) }
    }

    private fun titleOrGenreContainsQuery(movie: Movie, query: CharSequence) =
        titleContainsQuery(movie, query) || genreContainsQuery(movie, query)

    private fun genreContainsQuery(movie: Movie, query: CharSequence) =
        (movie.genre.contains(query, true))

    private fun titleContainsQuery(movie: Movie, query: CharSequence) =
        (movie.title.contains(query, true))
}
