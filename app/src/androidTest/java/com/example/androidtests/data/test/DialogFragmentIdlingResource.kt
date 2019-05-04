package com.example.androidtests.data.test

import androidx.fragment.app.FragmentManager
import androidx.test.espresso.IdlingResource

class DialogFragmentIdlingResource(
    private val manager: FragmentManager,
    private val tag: String
) : IdlingResource {

    private var callback: IdlingResource.ResourceCallback? = null

    override fun getName(): String = DialogFragmentIdlingResource::class.java.name + ":" + tag

    override fun isIdleNow(): Boolean {
        val idle = manager.findFragmentByTag(tag) == null
        if (idle) callback?.onTransitionToIdle()
        return idle
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.callback = callback
    }
}
