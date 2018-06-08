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
        oldList.get(oldItemPosition).id == newList.get(newItemPosition).id

    // But we do not expect that the ids stay the same but the content change
    // therefore we don't need to implement getPayload() TODO
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList.get(oldItemPosition).equals(newList.get(newItemPosition))

}