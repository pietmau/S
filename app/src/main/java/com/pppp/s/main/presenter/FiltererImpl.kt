package com.pppp.s.main.presenter

import com.pppp.s.main.model.pokos.Movie

class FiltererImpl : Filterer {
    override fun filter(movies: List<Movie>, query: String?, presenter: MainPresenter) {
        MoviesFilter(movies, presenter).filter(query)
    }
}
