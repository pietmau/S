package com.pppp.s.main.model

import android.arch.lifecycle.ViewModel
import com.pppp.s.main.api.Api
import com.pppp.s.main.model.pokos.Movie
import io.reactivex.Observable

class RetrofitModel(private val api: Api) : ViewModel(), MainModel {

    override fun subscribe(): Observable<List<Movie>> =
        api.getMovies().map { it.data ?: emptyList() }
}