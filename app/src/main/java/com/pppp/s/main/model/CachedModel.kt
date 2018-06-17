package com.pppp.s.main.model

import android.arch.lifecycle.ViewModel
import com.pppp.s.main.model.pokos.Movie
import io.reactivex.Observable

/** A model that caches the data. */
abstract class CachedModel: ViewModel() {
    abstract fun subscribe(): Observable<List<Movie>>
}
