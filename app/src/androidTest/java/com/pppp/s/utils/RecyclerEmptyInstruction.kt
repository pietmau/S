package com.pppp.s.utils

import android.app.Activity
import com.azimolabs.conditionwatcher.Instruction
import com.pppp.s.R
import com.pppp.s.main.view.custom.MoviesRecycler

/**
 * Waits for the recycler to bet empty
 */
class RecyclerEmptyInstruction(private val activity: Activity) : Instruction() {
    override fun getDescription(): String = "Wait for filtering to complete"

    override fun checkCondition(): Boolean {
        return activity.findViewById<MoviesRecycler>(R.id.recycler).adapter.itemCount == 0
    }
}
