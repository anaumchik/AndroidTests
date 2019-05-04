package com.example.androidtests.data.utils

import org.joda.time.DateTime

open class CustomClock {
    open fun getNow() = DateTime()
}