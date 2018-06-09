package com.pppp.s.main.view.custom

import android.support.v7.util.DiffUtil
import com.pppp.s.main.model.pokos.Movie

class MoviesDiffUtilCallback(
    private val oldList: List<Movie>,
    private val newList: List<Movie>
) :
    DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}
