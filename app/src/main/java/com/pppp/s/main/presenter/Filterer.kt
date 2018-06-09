package com.pppp.s.main.presenter

import com.pppp.s.main.model.pokos.Movie

/**
 * Wraps Android Filter
 */
interface Filterer {

    fun filter(movies: List<Movie>, query: String?, presenter: MainPresenter)
}
