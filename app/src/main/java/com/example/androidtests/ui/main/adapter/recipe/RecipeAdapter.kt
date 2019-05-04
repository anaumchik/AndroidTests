package com.example.androidtests.ui.main.adapter.recipe

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtests.R
import com.example.androidtests.data.local.RecipeStore
import com.example.androidtests.ui.recipe.RecipeActivity
import com.example.androidtests.ui.recipe.RecipeActivity.Companion.KEY_ID

class RecipeAdapter(private val store: RecipeStore) : RecyclerView.Adapter<RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder =
        RecipeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = store.recipes[position]
        holder.textView.text = recipe.title
        holder.textView.setOnClickListener {
            val context = holder.textView.context
            val intent = Intent(context, RecipeActivity::class.java)
            intent.putExtra(KEY_ID, recipe.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = store.recipes.size
}
