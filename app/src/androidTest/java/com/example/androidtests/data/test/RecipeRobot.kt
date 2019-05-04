package com.example.androidtests.data.test

import android.content.Intent
import androidx.annotation.StringRes
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.androidtests.R
import com.example.androidtests.data.injection.TestRecipeApplication
import com.example.androidtests.data.utils.InMemoryFavorites
import com.example.androidtests.ui.recipe.RecipeActivity

class RecipeRobot : ScreenRobot<RecipeRobot>() {
    var favorites: InMemoryFavorites

    init {
        val app = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as TestRecipeApplication
        favorites = app.getFavorites() as InMemoryFavorites
        favorites.clear()
    }

    fun launch(rule: ActivityTestRule<*>, id: String) {
        val intent = Intent()
        intent.putExtra(RecipeActivity.KEY_ID, id)
        rule.launchActivity(intent)
    }

    fun noTitle() = isNotDisplayed(R.id.tvTitle)

    fun isFavorite() = isSelected(R.id.tvTitle)

    fun isNotFavorite() = isNotSelected(R.id.tvTitle)

    fun title(text: String) = withText(R.id.tvTitle, text)

    fun click() = performClick(R.id.tvTitle)

    fun description(@StringRes stringId: Int) = withText(R.id.tvDesctiption, stringId)

    fun setFavorite(id: String) = favorites.put(id, true)

    companion object {
        fun withRecipeRobot(func: RecipeRobot.() -> Unit) = RecipeRobot().apply { func() }
    }
}
