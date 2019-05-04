package com.example.androidtests

import android.app.Application
import com.example.androidtests.data.local.Favorites
import com.example.androidtests.data.local.SharedPreferencesFavorites

open class MyApplication : Application() {
    private lateinit var favoritesImpl: Favorites

    open fun getFavorites(): Favorites {
        if (!::favoritesImpl.isInitialized) favoritesImpl = SharedPreferencesFavorites(this)
        return favoritesImpl
    }

    open val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .clockModule(ClockModule)
            .favoritesModule(FavoritesModule(this))
            .build()
    }
}
