package com.example.androidtests.ui.idle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidtests.R
import kotlinx.android.synthetic.main.activity_idle.*

class IdleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_idle)

        initDialog()
    }

    private fun initDialog() {
        val fragment = LoadingDialogFragment()
        fragment.isCancelable = false
        fragment.show(supportFragmentManager, LoadingDialogFragment.TAG)
    }

    fun onLoadingFinished() {
        tvLoading.setText(R.string.done)
    }

}
