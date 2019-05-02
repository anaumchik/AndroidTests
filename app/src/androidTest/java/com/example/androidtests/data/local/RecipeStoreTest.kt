package com.example.androidtests.data.local

import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class RecipeStoreTest {

    @Test
    fun nullDirectory() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val store = RecipeStore(context, null)
        assertNotNull(store)
        assertNotNull(store.recipes)
        assertEquals(0, store.recipes.size)
    }

    @Test
    fun count() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val store = RecipeStore(context, "recipes")
        assertNotNull(store)
        assertNotNull(store.recipes)
        assertEquals(4, store.recipes.size)
    }

    @Test
    fun getChocolatePudding() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val store = RecipeStore(context, "recipes")
        val recipe = store.getRecipe("chocolate_pudding")
        assertNotNull(recipe)

        recipe?.let {
            assertEquals("chocolate_pudding", recipe.id)
            assertEquals("Chocolate Pudding", recipe.title)
        }
    }

}
