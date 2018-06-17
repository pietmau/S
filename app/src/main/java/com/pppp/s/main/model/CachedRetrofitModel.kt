package com.pppp.s.main.model

import com.pppp.s.main.api.Api
import com.pppp.s.main.model.pokos.Movie
import com.pppp.s.main.model.pokos.MovieResponse
import io.reactivex.Observable

/**
 * It is retained across config changes via Architecture Components ViewModel.
 * It is important to unsubscribe to avoid leaks.
 *
 * Keeps track of the last connection, and a new Observable is created when time expires.
 */
class CachedRetrofitModel(
    private val api: Api,
    private val cacheExpiryTimeInSeconds: Long = 10 * 60
) : CachedModel() {
    private var lastConnectionTime = 0L

    private var cachedObservable: Observable<MovieResponse> = Observable.empty()
        get() = if (!isExpired()) {
            field
        } else {
            field = queryApi(api)
            field
        }

    override fun subscribe(): Observable<List<Movie>> =
        cachedObservable.map { it.data ?: emptyList() }

    private fun isExpired() =
        (System.currentTimeMillis() - lastConnectionTime) > cacheExpiryTimeInSeconds * 1000

    private fun queryApi(api: Api) = api.getMovies()
        // Stores the last successful connection
        .doOnComplete { lastConnectionTime = System.currentTimeMillis() }
        .cache()
}
