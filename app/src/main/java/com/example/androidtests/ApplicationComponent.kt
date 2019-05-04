package com.example.androidtests

import android.content.Context
import com.example.androidtests.data.local.Favorites
import com.example.androidtests.data.local.SharedPreferencesFavorites
import com.example.androidtests.data.utils.CustomClock
import com.example.androidtests.ui.main.MainActivity
import com.example.androidtests.ui.recipe.RecipeActivity
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [ClockModule::class, FavoritesModule::class])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(recipeActivity: RecipeActivity)
}

@Module
object ClockModule {
    @Provides
    fun getCustomClock(): CustomClock = CustomClock()
}

@Module
class FavoritesModule(private val application: MyApplication) {
    @Provides
    fun getContext(): Context = application

    @Provides
    fun getFavorites(context: Context): Favorites = SharedPreferencesFavorites(context)
}
