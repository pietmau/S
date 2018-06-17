package com.pppp.s.main.model.cache.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.pppp.s.main.model.pokos.Movie


@Dao
interface MoviesDao {
    @Query("SELECT * FROM movie")
    fun getAll(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Movie>)
}