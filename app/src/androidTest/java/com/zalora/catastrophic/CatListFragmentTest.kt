package com.zalora.catastrophic

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.zalora.catastrophic.home.viewholder.HomeViewHolder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class CatListFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(HomeActivity::class.java)

    val LIST_ITEM_IN_TEST = 4

    @Test
    fun test_isListFragmentVisible_onAppLaunch() {
        onView(withId(R.id.home_recycler)).check(matches(isDisplayed()))
    }

    @Test
    fun test_selectListItem_isDetailFragmentVisible() {
        // Click list item #LIST_ITEM_IN_TEST


        onView(withId(R.id.home_recycler))
            .perform(actionOnItemAtPosition<HomeViewHolder>(LIST_ITEM_IN_TEST, click()))

        onView(withId(R.id.detail_image)).check(matches(isDisplayed()))
    }

    @Test
    fun test_backNavigation_toMovieListFragment() {
        // Click list item #LIST_ITEM_IN_TEST
        onView(withId(R.id.home_recycler))
            .perform(actionOnItemAtPosition<HomeViewHolder>(LIST_ITEM_IN_TEST, click()))

        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.detail_image)).check(matches(isDisplayed()))

        pressBack()

        // Confirm MovieListFragment in view
        onView(withId(R.id.home_recycler)).check(matches(isDisplayed()))
    }
}