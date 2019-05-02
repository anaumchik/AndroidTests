package com.example.androidtests.data.local


import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesFavorites(context: Context) : Favorites {
    private val pref: SharedPreferences = context.getSharedPreferences("favorites.xml", Context.MODE_PRIVATE)


    override fun get(id: String): Boolean = pref.getBoolean(id, false)


    override fun toggle(id: String): Boolean {
        val favorite = get(id)
        put(id, !favorite)
        return !favorite
    }

    private fun put(id: String, favorite: Boolean) =
        pref.edit().apply { if (favorite) putBoolean(id, true) else remove(id) }.apply()
}
