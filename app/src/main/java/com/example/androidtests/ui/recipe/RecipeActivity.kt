package com.example.androidtests.ui.recipe

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.androidtests.R
import com.example.androidtests.data.injection.RecipeApplication
import com.example.androidtests.data.local.RecipeStore
import com.example.androidtests.data.local.SharedPreferencesFavorites
import kotlinx.android.synthetic.main.activity_recipe.*


class RecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        val store = RecipeStore(this, "recipes")
        val id = intent.getStringExtra(KEY_ID) ?: "id"
        val recipe = store.getRecipe(id)

        if (recipe == null) {
            tvTitle.visibility = View.GONE
            tvDesctiption.text = getString(R.string.recipe_not_found)
            return
        }

        val app = application as RecipeApplication

        val favorites = app.getFavorites()

        val favorite = favorites.get(recipe.id)
        tvTitle.text = recipe.title
        tvTitle.isSelected = favorite
        tvTitle.setOnClickListener {
            val result = favorites.toggle(recipe.id)
            tvTitle.isSelected = result
        }
        tvDesctiption.text = recipe.description

    }

    companion object {
        const val KEY_ID = "id"
    }
}
