package com.pppp.s.mocks

import com.pppp.s.main.model.CachedModel
import com.pppp.s.main.model.pokos.Movie
import io.reactivex.Observable

class MockModel(private var observable: Observable<List<Movie>> = Observable.empty()) :
    CachedModel {

    override fun subscribe(): Observable<List<Movie>> =
        observable
}
