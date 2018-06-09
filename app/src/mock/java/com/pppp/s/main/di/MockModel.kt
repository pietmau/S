package com.pppp.s.main.di

import com.pppp.s.main.model.CachedModel
import com.pppp.s.main.model.pokos.Movie
import io.reactivex.Observable

class MockModel : CachedModel {

    override fun subscribe(): Observable<List<Movie>> = observable

    companion object {
        var observable: Observable<List<Movie>> = Observable.empty()
    }
}
