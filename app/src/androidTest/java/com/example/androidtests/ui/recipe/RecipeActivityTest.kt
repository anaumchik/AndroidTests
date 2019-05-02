package com.example.androidtests.ui.recipe

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.androidtests.R
import com.example.androidtests.data.injection.TestRecipeApplication
import com.example.androidtests.ui.recipe.RecipeActivity.Companion.KEY_ID
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RecipeActivityTest {
    @get:Rule
    var activityRule = ActivityTestRule<RecipeActivity>(RecipeActivity::class.java)

    lateinit var favorites: InMemoryFavorites

    @Before
    fun clearFavorites() {
        val app = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as TestRecipeApplication
        favorites = app.getFavorites() as InMemoryFavorites
        favorites.clear()
    }

    @Test
    fun recipeNotFound() {
        onView(withId(R.id.tvTitle))
            .check(matches(not(isDisplayed())))
        onView(withId(R.id.tvDesctiption))
            .check(matches(withText(R.string.recipe_not_found)))
    }

    @Test
    fun clickToFavorite() {
        launchRecipe(CARROT_ID)

        onView(withId(R.id.tvTitle))
            .check(matches(withText("Creamed Carrots")))
            .check(matches(not(isSelected())))
            .perform(click())
            .check(matches(isSelected()))
    }

    @Test
    fun alreadyFavorite() {
        favorites.put(CARROT_ID, true)

        launchRecipe(CARROT_ID)

        onView(withId(R.id.tvTitle))
            .check(matches(isSelected()))
    }

    private fun launchRecipe(id: String) {
        val intent = Intent().putExtra(KEY_ID, id)
        activityRule.launchActivity(intent)
    }

    companion object {
        const val CARROT_ID = "creamed_carrots"
    }
}
