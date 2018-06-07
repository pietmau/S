package com.pppp.s.main.view

import com.pppp.s.main.model.pokos.Movie

interface MainView {
    fun onMoviesAvvailable(movies: List<Movie>?)
    fun onError(throwable: Throwable?)

}
