package com.pppp.s.utils

import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers
import android.support.v7.widget.SearchView
import android.view.View
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher

fun typeSearchViewText(text: String): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return CoreMatchers.allOf(
                ViewMatchers.isDisplayed(),
                ViewMatchers.isAssignableFrom(SearchView::class.java)
            )
        }

        override fun getDescription(): String {
            return "Change view text"
        }

        override fun perform(uiController: UiController, view: View) {
            (view as SearchView).setQuery(text, false)
        }
    }
}