package com.pppp.s.main.view.custom

import android.content.Context
import android.content.res.Configuration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import com.pppp.s.main.model.pokos.Movie

class MoviesRecycler @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) :
    RecyclerView(context, attrs, defStyle) {
    private val moviesAdapter
        get() = (adapter as MoviesAdapter)

    init {
        layoutManager = getLayoutManager(context)
        adapter = MoviesAdapter()
    }

    private fun getLayoutManager(context: Context) =
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            SimpleGridLayoutManager(context)
        } else {
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
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
