package com.pppp.s.main.model.cache.memory

import com.pppp.s.main.model.cache.Cache
import com.pppp.s.main.model.pokos.Movie

class SimpleMemoryCache() : Cache() {
    private var movies: List<Movie>? = null

    override fun getMovies(): List<Movie>? = movies

    override fun putMovies(movies: List<Movie>, timestamp: Long) {
        this.movies = ArrayList(movies.map { movie -> movie.copy(timestamp = timestamp) }.toList())
    }

}