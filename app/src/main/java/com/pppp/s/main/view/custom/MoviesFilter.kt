package com.pppp.s.main.view.custom

import android.widget.Filter
import com.pppp.s.main.model.pokos.Movie

class MoviesFilter(private val moviesAdapter: MoviesAdapter) : Filter() {

    override fun performFiltering(query: CharSequence?): FilterResults {
        val results = Filter.FilterResults()
        var movies: List<Movie>
        if (!query.isNullOrEmpty()) {
            movies = moviesAdapter.movies.filter { movie ->
                titleOrGenreContainsQuery(movie, query!!)
            }
        } else {
            movies = moviesAdapter.movies
        }
        results.count = movies.size
        results.values = movies.sorted()
        return results
    }

    override fun publishResults(cquery: CharSequence?, filteredResults: FilterResults?) {
        (filteredResults?.values as? List<Movie>)?.let { movies ->
            moviesAdapter.onMoviesFiltered(movies)
        }
    }

    private fun titleOrGenreContainsQuery(movie: Movie, charSequence: CharSequence): Boolean =
        (movie.genre?.contains(charSequence,true) == true) || (movie.title?.contains(charSequence,true) == true)
}
