package com.example.androidtests

import com.example.androidtests.data.utils.InMemoryFavorites

class TestMyApplication : MyApplication() {
    override lateinit var component: ApplicationComponent

    private val favorites = InMemoryFavorites()

    override fun getFavorites() = favorites
}
