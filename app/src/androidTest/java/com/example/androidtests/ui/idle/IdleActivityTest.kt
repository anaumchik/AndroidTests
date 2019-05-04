package com.example.androidtests.ui.idle

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.androidtests.R
import com.example.androidtests.data.test.DialogFragmentIdlingResource
import com.example.androidtests.ui.recipe.RecipeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IdleActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(IdleActivity::class.java)

    @Test
    fun done() {
        val idlingResource = DialogFragmentIdlingResource(
            activityRule.activity.supportFragmentManager, LoadingDialogFragment.TAG
        )
        IdlingRegistry.getInstance().register(idlingResource)

        onView(withId(R.id.tvLoading))
            .check(matches(withText(R.string.done)))

        IdlingRegistry.getInstance().unregister(idlingResource)

    }

}