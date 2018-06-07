package com.pppp.s.main.model


import com.pppp.s.main.model.pokos.Movie
import io.reactivex.Observable

interface MainModel {
    fun subscribe():Observable<List<Movie>>
}