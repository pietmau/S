package com.pppp.s.main.model

import com.pppp.s.main.model.pokos.Movie

interface Repository {
    fun getMovies(): List<Movie>

}