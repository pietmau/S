package com.pppp.s.main.model.pokos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("data")
    @Expose
    var data: List<Movie>? = null
)
