package com.pppp.s

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.hasDescendant
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.azimolabs.conditionwatcher.ConditionWatcher
import com.pppp.s.main.MainActivity
import com.pppp.s.main.di.MockModel
import com.pppp.s.utils.RecyclerEmptyInstruction
import com.pppp.s.utils.typeSearchViewText
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> =
        object : ActivityTestRule<MainActivity>(MainActivity::class.java) {
            override fun beforeActivityLaunched() {
                MockModel.observable = createObservableFromJson()
            }
        }

    @Test
    fun whenGetsMoviesTheyShow() {
        onView(withId(R.id.recycler)).check(matches(hasDescendant(withText(LUCY))))
    }

    @Test
    fun whenFilteredDisappears() {
        onView(withId(R.id.searchView)).perform(typeSearchViewText(SOME_TEXT))
        ConditionWatcher.waitForCondition(RecyclerEmptyInstruction(activityRule.activity))
        onView(withId(R.id.recycler)).check(matches(not(hasDescendant(withText(LUCY)))))
    }

    companion object {
        private const val LUCY = "Lucy"
        private const val SOME_TEXT = "some_text"
    }
}
