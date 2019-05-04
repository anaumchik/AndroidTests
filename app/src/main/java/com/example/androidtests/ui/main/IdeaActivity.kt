package com.example.androidtests.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtests.R
import com.example.androidtests.ui.main.adapter.idea.IdeaAdapter
import kotlinx.android.synthetic.main.activity_ideas.*

class IdeaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ideas)

        initIdeas()
    }

    private fun initIdeas() {
        val theme = intent.getStringExtra(KEY_THEME)
        if (theme == null) {
            tvTheme.setText(R.string.missing_theme)
            return
        }

        tvTheme.text = theme

        val ideasId = when (theme) {
            getString(R.string.theme_popular) -> R.array.ideas_popular
            getString(R.string.theme_famous) -> R.array.ideas_famous
            getString(R.string.theme_punny) -> R.array.ideas_punny
            else -> 0
        }

        if (ideasId == 0) {
            tvTheme.text = getString(R.string.unknown_theme, theme)
            return
        }

        val ideas = resources.getStringArray(ideasId)

        rvIdeas.setHasFixedSize(true)
        rvIdeas.layoutManager = LinearLayoutManager(this)
        rvIdeas.adapter = IdeaAdapter(ideas, object : IdeaAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val data = Intent()
                    .putExtra(KEY_NAME, ideas[position])
                setResult(Activity.RESULT_OK, data)
                finish()
            }
        })
    }

    companion object {
        const val KEY_THEME = "theme"
        const val KEY_NAME = "name"
    }
}