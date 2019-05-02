package com.example.androidtests.ui.recipe

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.androidtests.R
import com.example.androidtests.data.injection.RecipeApplication
import com.example.androidtests.data.local.RecipeStore
import kotlinx.android.synthetic.main.activity_recipe.*


class RecipeActivity : AppCompatActivity(), RecipeContract.View {

    lateinit var presenter: RecipePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        val id = intent.getStringExtra(KEY_ID) ?: KEY_ID

        val store = RecipeStore(this, "recipes")
        val favorites = (application as RecipeApplication).getFavorites()

        presenter = RecipePresenter(store, this, favorites)
        presenter.loadRecipe(id)

        tvTitle.setOnClickListener { presenter.toggleFavorite() }
    }

    override fun showRecipeNotFoundError() {
        tvTitle.visibility = View.GONE
        tvDesctiption.text = getString(R.string.recipe_not_found)
    }

    override fun setTitle(title: String) {
        tvTitle.text = title
    }

    override fun setDescription(description: String) {
        tvDesctiption.text = description
    }

    override fun setFavorite(favorite: Boolean) {
        tvTitle.isSelected = favorite
    }

    companion object {
        const val KEY_ID = "id"
    }
}
