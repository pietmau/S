package com.pppp.s.main.model.cache.database

import android.arch.persistence.room.Room
import android.content.Context
import com.pppp.s.main.model.cache.Cache
import com.pppp.s.main.model.pokos.Movie


class DatabaseCache(context: Context) : Cache() {
    private var db: MoviesDatabase

    init {
        val appContext = context.getApplicationContext()
        db = Room.databaseBuilder(appContext, MoviesDatabase::class.java, "database").build()
    }
    @Synchronized
    override fun getMovies(): List<Movie>? = db.moviesdao().getAll()

    @Synchronized
    override fun putMovies(movies: List<Movie>, timestamp: Long) {
        db.moviesdao().insertAll(movies.map { movie -> movie.copy(timestamp = timestamp) })
    }
}