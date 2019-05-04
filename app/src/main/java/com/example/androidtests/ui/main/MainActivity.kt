package com.example.androidtests.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtests.MyApplication
import com.example.androidtests.R
import com.example.androidtests.data.local.RecipeStore
import com.example.androidtests.data.utils.CustomClock
import com.example.androidtests.ui.idea.IdeaActivity
import com.example.androidtests.ui.idle.IdleActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var customClock: CustomClock

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as MyApplication).component.inject(this)

        initGreetings()
        initRecipes()
    }

    private fun initGreetings() {
        val greetingId = when (customClock.getNow().hourOfDay) {
            in 5..12 -> R.string.greeting_morning
            in 12..17 -> R.string.greeting_afternoon
            in 17..23 -> R.string.greeting_evening
            else -> R.string.greeting_night
        }

        tvGreeting.setText(greetingId)
    }

    private fun initRecipes() {
        val store = RecipeStore(this, "recipes")
        val adapter = RecipeAdapter(store)
        recipes.adapter = adapter
        recipes.setHasFixedSize(true)
        recipes.layoutManager = LinearLayoutManager(this)
    }

    fun goToIdeaActivity(v: View) {
        val intent = Intent(this, IdeaActivity::class.java)
            .putExtra(IdeaActivity.KEY_THEME, (v as Button).text)
        startActivityForResult(intent, REQUEST_CODE_IDEAS)
    }

    fun goToIdleActivity(v: View) = startActivity(Intent(this, IdleActivity::class.java))

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) = when (requestCode) {
        REQUEST_CODE_IDEAS -> (etName as TextView).text = data?.getStringExtra(IdeaActivity.KEY_NAME)
        else -> super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        private const val REQUEST_CODE_IDEAS = 1
    }
}
