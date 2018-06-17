package com.pppp.s.main.model.pokos

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Movie(
    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: Int = 0,
    @SerializedName("title")
    @Expose
    var title: String = "",
    @SerializedName("year")
    @Expose
    var year: String? = null,
    @SerializedName("genre")
    @Expose
    var genre: String = "",
    @SerializedName("poster")
    @Expose
    var poster: String? = null,
    var timestamp: Long = 0

) : Comparable<Movie> {

    override fun compareTo(other: Movie) = title.compareTo(other.title)
}
