package com.pppp.s.main.api

import com.pppp.s.main.model.pokos.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface Api {

    @GET("api/movies")
    fun getMovies(): Observable<MovieResponse>//TODO use interceptor instead
}
