package com.example.androidtests.ui.idle

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.format.DateUtils
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.androidtests.R
import java.lang.ref.WeakReference

class LoadingDialogFragment : DialogFragment() {

    private val handler = LoadingHandler(this)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        handler.sendEmptyMessageDelayed(LoadingHandler.MSG_DISMISS, DELAY)

        return AlertDialog.Builder(requireActivity())
            .setTitle(R.string.loading)
            .setMessage(R.string.please_wait)
            .create()
    }

    override fun onDestroyView() {
        handler.removeMessages(LoadingHandler.MSG_DISMISS)
        super.onDestroyView()
    }

    companion object {
        const val TAG = "Loading"
        private const val DELAY = DateUtils.SECOND_IN_MILLIS * 3
    }

    private class LoadingHandler(fragment: DialogFragment) : Handler() {
        companion object {
            const val MSG_DISMISS = 0
        }

        private val ref = WeakReference(fragment)

        override fun handleMessage(msg: Message) {
            val fragment = ref.get() ?: return
            fragment.dismiss()
            val activity = fragment.activity
            (activity as? IdleActivity)?.onLoadingFinished()
        }
    }
}
