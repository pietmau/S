package com.pppp.s.main.model

import android.arch.lifecycle.ViewModel
import com.pppp.s.main.api.Api
import com.pppp.s.main.model.pokos.Movie
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class CachedRetrofitModel(api: Api, cacheExpiryTimeInMinutes: Long = 10) : ViewModel(), CahedModel {
    private val cachedObservable = api.getMovies()
        .replay(cacheExpiryTimeInMinutes, TimeUnit.MINUTES)
        .autoConnect()

    override fun subscribe(): Observable<List<Movie>> =
        cachedObservable.map { it.data ?: emptyList() }
}