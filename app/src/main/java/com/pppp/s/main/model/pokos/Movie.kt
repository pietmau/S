package com.pppp.s.main.model.pokos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Movie(
    @SerializedName("id")
    @Expose
    var id: Int = 0,
    @SerializedName("title")
    @Expose
    var title: String? = null,
    @SerializedName("year")
    @Expose
    var year: String? = null,
    @SerializedName("genre")
    @Expose
    var genre: String? = null,
    @SerializedName("poster")
    @Expose
    var poster: String? = null
)