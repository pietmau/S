package com.pppp.s.main.model.cache.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.pppp.s.main.model.pokos.Movie

@Database(entities = arrayOf(Movie::class), version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesdao(): MoviesDao
}