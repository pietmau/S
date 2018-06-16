package com.pppp.s.utils

import android.app.Activity
import android.support.test.espresso.IdlingResource
import com.pppp.s.R
import com.pppp.s.main.view.custom.MoviesRecycler

class EmptyRecyclerIdlingResource(private val activity: Activity) :
    IdlingResource {
    private var callback: IdlingResource.ResourceCallback? = null

    override fun getName() = "Wait for recycler to become empty"

    override fun isIdleNow(): Boolean {
        val isIdle = activity.findViewById<MoviesRecycler>(R.id.recycler).adapter.itemCount == 0
        if (isIdle) {
            callback?.onTransitionToIdle()
        }
        return isIdle
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.callback = callback
    }
}