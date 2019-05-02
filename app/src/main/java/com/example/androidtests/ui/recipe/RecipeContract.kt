package com.example.androidtests.ui.recipe

interface RecipeContract {
    interface View {
        fun showRecipeNotFoundError()
        fun setTitle(title: String)
        fun setDescription(description: String)
        fun setFavorite(favorite: Boolean)
    }

    interface Listener {

    }
}