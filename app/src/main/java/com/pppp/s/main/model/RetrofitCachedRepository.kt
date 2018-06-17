package com.pppp.s.main.model

import com.pppp.s.main.api.Api
import com.pppp.s.main.model.cache.Cache
import com.pppp.s.main.model.pokos.Movie


class RetrofitCachedRepository(
    private val api: Api,
    private val cache: Cache
) : Repository {

    override fun getMovies(): List<Movie> = cache.getMovies() ?: getMoviesFromNetwork()

    private fun getMoviesFromNetwork(): List<Movie> {
        val movies = getMoviesSync()
        if (movies != null) {
            cache.putMovies(movies)
        }
        return movies ?: emptyList()
    }

    private fun getMoviesSync() = api.getMoviesSync().execute().body()?.data
}