package com.pppp.s.main.view.custom

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import com.pppp.s.main.model.pokos.Movie

class MoviesRecycler @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) :
    RecyclerView(context, attrs, defStyle) {
    private val numOfRows: Int = 3
    private val moviesAdapter
        get() = (adapter as MoviesAdapter)

    init {
        //TODO change based on orientation
        layoutManager = GridLayoutManager(context, numOfRows)
        adapter = MoviesAdapter()
    }

    fun onNewData(movies: List<Movie>) {
        moviesAdapter.onNewData(movies)
    }

    fun bind() {
        moviesAdapter.bind()
    }

    fun unbind() {
        moviesAdapter.unbind()
    }
}
