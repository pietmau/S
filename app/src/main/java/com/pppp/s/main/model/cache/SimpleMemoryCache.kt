package com.pppp.s.main.model.cache

import com.pppp.s.main.model.pokos.Movie

class SimpleMemoryCache(private val expiryInMills: Long) : Cache() {
    private var movies: List<Movie>? = null
    private var insertionTime = 0L

    override fun getMovies(): List<Movie>? = if (isExpired()) {
        null
    } else {
        movies
    }

    override fun putMovies(movies: List<Movie>) {
        this.movies = ArrayList(movies)
        insertionTime = System.currentTimeMillis()
    }

    private fun isExpired() = ((movies == null) || isActuallyExpired())

    private fun isActuallyExpired() = (System.currentTimeMillis() - insertionTime) > expiryInMills
}