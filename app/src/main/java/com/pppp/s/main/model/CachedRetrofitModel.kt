package com.pppp.s.main.model

import android.arch.lifecycle.ViewModel
import com.pppp.s.main.api.Api
import com.pppp.s.main.model.pokos.Movie
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * Data are cached using RxJava.
 */
class CachedRetrofitModel(
    api: Api,
    cacheExpiryTimeInMinutes: Long = 10
) : ViewModel(),
    CachedModel {
    private val cachedObservable = api.getMovies()
        .replay(cacheExpiryTimeInMinutes, TimeUnit.MINUTES)
        .autoConnect()

    override fun subscribe(): Observable<List<Movie>> =
        cachedObservable.map { it.data ?: emptyList() }
}