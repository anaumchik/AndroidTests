package com.example.androidtests.data.utils

import com.example.androidtests.data.local.Favorites

class InMemoryFavorites : Favorites {
    private val map = HashMap<String, Boolean>()

    override fun get(id: String): Boolean = map[id] ?: false

    override fun toggle(id: String): Boolean {
        val value = get(id)
        map[id] = !value
        return !value
    }

    fun put(id: String, favorite: Boolean) {
        map[id] = favorite
    }

    fun clear() = map.clear()

}