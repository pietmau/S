package com.pppp.s.main.model

import com.pppp.s.main.model.pokos.Movie
import io.reactivex.Observable

/** A model that caches the data. */
interface CachedModel {
    fun subscribe(): Observable<List<Movie>>
}
