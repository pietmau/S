package com.pppp.s.utils

import android.app.Activity
import android.content.res.Configuration
import com.azimolabs.conditionwatcher.Instruction

class OrientationInstruction(private val activity: Activity) : Instruction() {
    override fun getDescription() = "Wait for orientation to change"

    override fun checkCondition() =
        activity.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
}

