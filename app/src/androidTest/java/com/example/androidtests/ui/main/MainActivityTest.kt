package com.example.androidtests.ui.main

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.ComponentNameMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.androidtests.R
import com.example.androidtests.TestMyApplication
import com.example.androidtests.ui.idea.IdeaActivity
import org.joda.time.DateTime
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var activityRule = IntentsTestRule(MainActivity::class.java, true, false)

    @Test
    fun evening() {
        val app = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as TestMyApplication
        val clock = app.getCustomClock()

        Mockito.`when`(clock.getNow()).thenReturn(DateTime().withHourOfDay(20))

        activityRule.launchActivity(null)

        onView(withId(R.id.tvGreeting))
            .check(matches(withText(R.string.greeting_evening)))
    }

    @Test
    fun punnyLaunchActivity() {
        activityRule.launchActivity(Intent())
        onView(withId(R.id.button_punny))
            .perform(click())
        onView(withId(R.id.tvTheme))
            .check(matches(withText(R.string.theme_punny)))
    }

    @Test
    fun punnyIntended() {
        activityRule.launchActivity(Intent())
        onView(withId(R.id.button_punny))
            .perform(click())

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val theme = context.getString(R.string.theme_punny)
        Intents.intended(hasExtra(IdeaActivity.KEY_THEME, theme))
        Intents.intended(
            IntentMatchers.hasComponent(
                ComponentNameMatchers.hasClassName("com.example.androidtests.ui.idea.IdeaActivity")
            )
        )
    }

    @Test
    fun punnyIntending() {
        activityRule.launchActivity(Intent())
        val name = "Catalie Portman"
        val intent = Intent().putExtra(IdeaActivity.KEY_NAME, name)
        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, intent)

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val theme = context.getString(R.string.theme_punny)
        Intents.intending(hasExtra(IdeaActivity.KEY_THEME, theme)).respondWith(result)

        onView(withId(R.id.button_punny))
            .perform(click())
        onView(withId(R.id.etName))
            .check(matches(withText(name)))
    }
}
