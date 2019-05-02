package com.example.androidtests.data.injection

import com.example.androidtests.data.local.Favorites
import com.example.androidtests.ui.recipe.InMemoryFavorites

class TestRecipeApplication : RecipeApplication() {
    private val favorites = InMemoryFavorites()

    override fun getFavorites(): Favorites = favorites

}
