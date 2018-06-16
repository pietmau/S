package com.pppp.s

import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.pppp.s.main.MainActivity
import com.pppp.s.utils.DaggerTestRule
import com.pppp.s.utils.EmptyRecyclerIdlingResource
import com.pppp.s.utils.RotationIdlingResource
import com.pppp.s.utils.typeSearchViewText
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var daggerTestRule = DaggerTestRule(createObservableFromJson())

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun whenGetsMoviesTheyShow() {
        val rotationIdlingResource = RotationIdlingResource(activityRule.activity)
        IdlingRegistry.getInstance().register(rotationIdlingResource)
        //The test will fail in landscape because there is not enough space on screen
        activityRule.getActivity().setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);
        onView(withId(R.id.recycler)).check(matches(hasDescendant(withText(LUCY))))
        IdlingRegistry.getInstance().unregister(rotationIdlingResource)
    }

    @Test
    fun whenFilteredDisappears() {
        val idlingResource = EmptyRecyclerIdlingResource(activityRule.activity)
        onView(withId(R.id.searchView)).perform(typeSearchViewText(SOME_TEXT))
        IdlingRegistry.getInstance().register(idlingResource)
        onView(withId(R.id.recycler)).check(matches(not(hasDescendant(withText(LUCY)))))
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    companion object {
        private const val LUCY = "Lucy"
        private const val SOME_TEXT = "some_text"
    }
}
