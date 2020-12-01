package com.rphmelo.githubpop.extension

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.rphmelo.githubpop.R

fun Context.showMessageDialog(title: String, message: String, tryAgainAction: (() -> Unit)? = null) {
    AlertDialog.Builder(this).create().apply {
        setTitle(title)
        setMessage(message)

        setButton(DialogInterface.BUTTON_POSITIVE, resources.getString(R.string.try_again)) { _, _ ->
            tryAgainAction?.invoke()
            dismiss()
        }
    }.show()
}