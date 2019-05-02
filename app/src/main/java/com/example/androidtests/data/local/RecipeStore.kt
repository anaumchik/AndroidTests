package com.example.androidtests.data.local

import android.content.Context
import android.content.res.AssetManager
import com.example.androidtests.data.model.Recipe
import java.io.File
import java.io.IOException
import java.io.InputStream

class RecipeStore(context: Context, directory: String?) {

    private val map = HashMap<String, Recipe>()
    val recipes: ArrayList<Recipe> = arrayListOf()

    init {
        val streams = getAssetStreams(context.assets, directory)
        for (stream: InputStream in streams) {
            val recipe = Recipe.readFromStream(stream)
            if (recipe != null) {
                recipes.add(recipe)
                map[recipe.id] = recipe
            }
        }
    }

    fun getRecipe(id: String): Recipe? = map[id]

    companion object {
        private fun getAssetStreams(manager: AssetManager, directory: String?): List<InputStream> {
            val filenames = getFilenames(manager, directory)
            val streams = arrayListOf<InputStream>()
            for (filename: String in filenames) {
                val file = File(directory, filename)
                try {
                    val stream = manager.open(file.path)
                    streams.add(stream)
                } catch (ex: IOException) {

                }
            }
            return streams
        }

        private fun getFilenames(manager: AssetManager, directory: String?): Array<String> {
            return try {
                if (directory != null) manager.list(directory) ?: emptyArray()
                else emptyArray()
            } catch (ex: IOException) {
                emptyArray()
            }
        }
    }

}
