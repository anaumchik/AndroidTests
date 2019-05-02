package com.example.androidtests.data.injection

import android.app.Application
import com.example.androidtests.data.local.Favorites
import com.example.androidtests.data.local.SharedPreferencesFavorites

open class RecipeApplication : Application() {
    lateinit var favoritesImpl: Favorites

    open fun getFavorites(): Favorites {
        if (!::favoritesImpl.isInitialized) favoritesImpl = SharedPreferencesFavorites(this)
        return favoritesImpl
    }
}