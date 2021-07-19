package com.dicoding.courseschedule.ui.home

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dicoding.courseschedule.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @Test
    fun openAddCourse() {
        onView(withId(R.id.view_home))
            .check(matches(isDisplayed()))
        onView(withId(R.id.action_add))
            .perform(ViewActions.click())
        onView(withId(R.id.add_course))
            .check(matches(isDisplayed()))
        onView(withId(R.id.add_lecturer))
            .check(matches(isDisplayed()))
        onView(withId(R.id.add_note))
            .check(matches(isDisplayed()))
        onView(withId(R.id.spinner_day))
            .check(matches(isDisplayed()))
        onView(withId(R.id.timeStart))
            .check(matches(isDisplayed()))
        onView(withId(R.id.timeEnd))
            .check(matches(isDisplayed()))
        onView(withId(R.id.action_insert))
            .check(matches(isDisplayed()))
    }
}