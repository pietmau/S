package com.pppp.s

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.pppp.s.main.MainActivity
import com.pppp.s.main.di.MockModel
import io.reactivex.Observable
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ErrorTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> =
        object : ActivityTestRule<MainActivity>(MainActivity::class.java) {
            override fun beforeActivityLaunched() {
                MockModel.observable = Observable.error(Exception(ERROR))
            }
        }

    @Test
    fun whenErrorShowSnackBar() {
        onView(withText(ERROR)).check(matches((isDisplayed())))
    }

    @Test
    fun whenErrorShowsNothing() {
        assertTrue(activityRule.activity.findViewById<RecyclerView>(R.id.recycler).adapter.itemCount == 0)
    }

    companion object {
        private const val ERROR = "Error"
    }
}
