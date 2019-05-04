package com.example.androidtests

import com.example.androidtests.data.utils.CustomClock
import com.example.androidtests.data.utils.InMemoryFavorites
import org.mockito.Mockito

class TestMyApplication : MyApplication() {
    private val clock by lazy { Mockito.mock(CustomClock::class.java) }
    private val favorites = InMemoryFavorites()

    override fun getFavorites() = favorites

    override fun getCustomClock() = clock

}
