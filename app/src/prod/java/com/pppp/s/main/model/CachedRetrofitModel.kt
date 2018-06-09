package com.pppp.s.main.model

import android.arch.lifecycle.ViewModel
import com.pppp.s.main.api.Api
import com.pppp.s.main.model.pokos.Movie
import com.pppp.s.main.model.pokos.MovieResponse
import io.reactivex.Observable

/**
 * Is retained across config changes (via Architecture Components ViewModel),
 * it is important to unsubscribe to avoid leaks.
 *
 * Keeps track of the last attempted connection, and a new Observable is created when time expires.
 *
 * RxJava Observable.replay(window,TimeUnit) replays the events within, but outside of the time window
 * becomes empty.
 */
class CachedRetrofitModel(
    private val api: Api,
    private val cacheExpiryTimeInSeconds: Long = 10 * 60
) : ViewModel(),
    CachedModel {
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
        // Stores the last connection (successful or not), called only at the time of the first subscription
        .doOnComplete { lastConnectionTime = System.currentTimeMillis() }
        .cache()// Cached so that further connections do not occur
}