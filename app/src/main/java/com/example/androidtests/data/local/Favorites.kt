package com.example.androidtests.data.local

interface Favorites {
    fun get(id: String): Boolean
    fun toggle(id: String): Boolean
}