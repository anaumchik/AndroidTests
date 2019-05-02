package com.example.androidtests.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtests.R
import com.example.androidtests.data.local.RecipeStore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val store = RecipeStore(this, "recipes")
        val adapter = RecipeAdapter(store)
        recipes.adapter = adapter
        recipes.setHasFixedSize(true)
        recipes.layoutManager = LinearLayoutManager(this)
    }

}
