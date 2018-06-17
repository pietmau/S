package com.pppp.s.main.model

import com.pppp.s.main.model.pokos.Movie
import com.pppp.s.main.model.repository.Repository
import io.reactivex.Observable

class ModelWithRepository(private val repo: Repository) : CachedModel() {

    override fun subscribe(): Observable<List<Movie>> = Observable.fromCallable { repo.getMovies() }
}