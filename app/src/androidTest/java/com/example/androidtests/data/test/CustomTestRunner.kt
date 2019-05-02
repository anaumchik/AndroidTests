package com.example.androidtests.data.test

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.example.androidtests.data.injection.TestRecipeApplication

class CustomTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, TestRecipeApplication::class.java.name, context)
    }

}
