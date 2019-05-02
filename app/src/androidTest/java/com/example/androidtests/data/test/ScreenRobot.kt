package com.example.androidtests.data.test

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.not

open class ScreenRobot<T> {
    fun isNotDisplayed(@IdRes viewId: Int): ViewInteraction =
        onView(withId(viewId))
            .check(matches(not(isDisplayed())))

    fun withText(@IdRes viewId: Int, @StringRes stringId: Int): ViewInteraction =
        onView(withId(viewId))
            .check(matches(withText(stringId)))

    fun withText(@IdRes viewId: Int, text: String): ViewInteraction =
        onView(withId(viewId))
            .check(matches(withText(text)))

    fun isSelected(@IdRes viewId: Int): ViewInteraction =
        onView(withId(viewId))
            .check(matches(isSelected()))

    fun isNotSelected(@IdRes viewId: Int): ViewInteraction =
        onView(withId(viewId))
            .check(matches(not(isSelected())))

    fun performClick(@IdRes viewId: Int): ViewInteraction =
        onView(withId(viewId))
            .perform(click())
}
