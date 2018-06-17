package com.pppp.s.main.model.cache

import com.pppp.s.main.model.cache.database.DatabaseCache
import com.pppp.s.main.model.cache.memory.SimpleMemoryCache
import com.pppp.s.main.model.pokos.Movie

class CompositeCache(
    private val expiryInMills: Long = 10 * 60 * 1000,
    private val memoryCache: SimpleMemoryCache,
    private val databaseCache: DatabaseCache
) : Cache() {

    override fun getMovies(): List<Movie>? = getFromMemory() ?: getFromDb()

    private fun getFromMemory(): List<Movie>? = memoryCache.getMovies()

    private fun getFromDb(): List<Movie>? {
        val movies = databaseCache.getMovies()
        if (movies != null && !areExpired(movies)) {
            memoryCache.putMovies(movies, getTimeStamp(movies))
            return movies
        }
        return null
    }

    private fun areExpired(movies: List<Movie>) =
        (System.currentTimeMillis() - getTimeStamp(movies)) > expiryInMills

    private fun getTimeStamp(movies: List<Movie>) = movies.firstOrNull()?.timestamp ?: 0

    override fun putMovies(movies: List<Movie>, timestamp: Long) {
        memoryCache.putMovies(movies, timestamp)
        databaseCache.putMovies(movies, timestamp)
    }
}