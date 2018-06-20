package com.pppp.s.main.model.cache

import com.pppp.s.main.model.pokos.Movie

class CompositeCache(
    private val expiryInMills: Long = 10 * 60 * 1000,
    private val memoryCache: Cache,
    private val persistedCache: Cache
) : Cache() {

    override fun getMovies(): List<Movie>? = getFromMemory() ?: getFromPersistentStorage()

    @Synchronized
    private fun getFromMemory(): List<Movie>? {
        val movies = memoryCache.getMovies()
        if (movies != null && !areExpired(movies)) {
            return movies
        }
        return null
    }

    private fun getFromPersistentStorage(): List<Movie>? {
        val movies = persistedCache.getMovies()
        if (movies != null && !areExpired(movies)) {
            memoryCache.putMovies(movies, getTimeStamp(movies))
            return movies
        }
        return null
    }

    private fun areExpired(movies: List<Movie>) =
        (System.currentTimeMillis() - getTimeStamp(movies)) > expiryInMills

    private fun getTimeStamp(movies: List<Movie>) = movies.firstOrNull()?.timestamp ?: 0

    @Synchronized
    override fun putMovies(movies: List<Movie>, timestamp: Long) {
        memoryCache.putMovies(movies, timestamp)
        persistedCache.putMovies(movies, timestamp)
    }
}