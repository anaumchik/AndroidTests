package com.example.androidtests.ui.recipe

import com.example.androidtests.data.local.Favorites
import com.example.androidtests.data.local.RecipeStore
import com.example.androidtests.data.model.Recipe
import kotlinx.android.synthetic.main.activity_recipe.*

class RecipePresenter(
    private val store: RecipeStore,
    private val view: RecipeContract.View,
    private val favorites: Favorites
) : RecipeContract.Listener {

    lateinit var recipe: Recipe

    fun loadRecipe(id: String) {
        store.getRecipe(id)?.let {
            recipe = it
            view.setTitle(it.title)
            view.setDescription(it.description)
            view.setFavorite(favorites.get(it.id))
        } ?: run {
            view.showRecipeNotFoundError()
        }
    }

    fun toggleFavorite() {
        val favorite = favorites.toggle(recipe.id)
        view.setFavorite(favorite)
    }
}