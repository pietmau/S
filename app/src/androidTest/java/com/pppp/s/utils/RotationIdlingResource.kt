package com.pppp.s.utils

import android.app.Activity
import android.content.res.Configuration
import android.support.test.espresso.IdlingResource

class RotationIdlingResource(private val activity: Activity) :
    IdlingResource {
    private var callback: IdlingResource.ResourceCallback? = null

    override fun getName() = "Wait for orientation to change"

    override fun isIdleNow(): Boolean {
        val resources = activity.resources
        val idle = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        if (idle) {
            callback?.onTransitionToIdle()
        }
        return idle
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.callback = callback
    }
}