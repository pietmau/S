package com.pppp.s.main.model.cache

import android.arch.lifecycle.ViewModel
import com.pppp.s.main.model.pokos.Movie

abstract class Cache : ViewModel() {
    abstract fun getMovies(): List<Movie>?
    abstract fun putMovies(movies: List<Movie>)
}