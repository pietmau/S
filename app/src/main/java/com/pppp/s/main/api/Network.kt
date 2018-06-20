package com.pppp.s.main.api

import com.pppp.s.main.model.pokos.MovieResponse
import io.reactivex.Observable
import retrofit2.Call

interface Network {

    fun getMovies(): Observable<MovieResponse>

    fun getMoviesSync(): Call<MovieResponse>
}