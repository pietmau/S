package com.pppp.s

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.pppp.s.main.MainActivity
import com.pppp.s.utils.DaggerTestRule
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ErrorTest {

    @get:Rule
    var dependencyInjectionRule = DaggerTestRule(Observable.error(Exception(ERROR)))

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> =
        ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun whenError_ShowSnackBar() {
        onView(withText(ERROR)).check(matches((isDisplayed())))
    }

    @Test
    fun whenError_ShowsNothing() {
        val recycler = activityRule.activity.findViewById<RecyclerView>(R.id.recycler)
        val itemCount = recycler.adapter.itemCount
        assertEquals(0, itemCount)
    }

    companion object {
        private const val ERROR = "Error"
    }
}
