package com.example.androidtests.ui.recipe

import androidx.test.rule.ActivityTestRule
import com.example.androidtests.R
import com.example.androidtests.data.test.RecipeRobot.Companion.withRecipeRobot
import org.junit.Rule
import org.junit.Test

class RecipeActivityTest {
    @get:Rule
    var activityRule = ActivityTestRule<RecipeActivity>(RecipeActivity::class.java)

    @Test
    fun recipeNotFound() {
        withRecipeRobot {
            noTitle()
            description(R.string.recipe_not_found)
        }
    }

    @Test
    fun clickToFavorite() {
        withRecipeRobot {
            launch(activityRule, CARROT_ID)
            title("Creamed Carrots")
            isNotFavorite()
            click()
            isFavorite()
        }
    }


    @Test
    fun alreadyFavorite() {
        withRecipeRobot {
            setFavorite(CARROT_ID)
            launch(activityRule, CARROT_ID)
            isFavorite()
        }
    }

    companion object {
        const val CARROT_ID = "creamed_carrots"
    }
}
