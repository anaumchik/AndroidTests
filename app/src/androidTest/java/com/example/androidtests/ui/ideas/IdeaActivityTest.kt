package com.example.androidtests.ui.ideas

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.androidtests.R
import com.example.androidtests.ui.idea.IdeaActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IdeaActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(IdeaActivity::class.java)

    @Test
    fun noTheme() {
        onView(withId(R.id.tvTheme))
            .check(matches(withText(R.string.missing_theme)))
    }
}
