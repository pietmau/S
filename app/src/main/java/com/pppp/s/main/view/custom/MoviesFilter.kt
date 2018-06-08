package com.pppp.s.main.view.custom

import android.widget.Filter
import com.pppp.s.main.model.pokos.Movie
import com.pppp.s.main.presenter.MainPresenter

class MoviesFilter(
    private val data: List<Movie>,
    private val presenter: MainPresenter
) : Filter() {

    override fun performFiltering(query: CharSequence?): FilterResults {
        val results = Filter.FilterResults()
        val movies: MutableList<Movie> = mutableListOf()
        if (!query.isNullOrEmpty()) {
            movies.addAll(data.filter { movie -> titleOrGenreContainsQuery(movie, query!!) })
        } else {
            movies.addAll(data)
        }
        results.count = movies.size
        results.values = movies
        return results
    }

    override fun publishResults(cquery: CharSequence?, filteredResults: FilterResults?) {
        (filteredResults?.values as? List<Movie>)?.let { movies -> presenter.onNewData(movies) }
    }

    private fun titleOrGenreContainsQuery(movie: Movie, charSequence: CharSequence): Boolean =
        (movie.genre?.contains(charSequence, true) == true)
                ||
                (movie.title?.contains(charSequence, true) == true)
}
