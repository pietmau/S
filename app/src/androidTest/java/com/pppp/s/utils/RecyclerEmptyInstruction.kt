package com.pppp.s.utils

import android.app.Activity
import com.azimolabs.conditionwatcher.Instruction
import com.pppp.s.R
import com.pppp.s.main.view.custom.MoviesRecycler

class RecyclerEmptyInstruction(private val activity: Activity) : Instruction() {
    override fun getDescription() = "Wait for filtering to complete"

    override fun checkCondition() =
        activity.findViewById<MoviesRecycler>(R.id.recycler).adapter.itemCount == 0
}

