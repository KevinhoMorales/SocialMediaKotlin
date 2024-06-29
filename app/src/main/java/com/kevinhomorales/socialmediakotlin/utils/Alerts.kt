package com.kevinhomorales.socialmediakotlin.utils

import android.app.AlertDialog
import android.content.Context
import androidx.appcompat.view.ContextThemeWrapper
import com.kevinhomorales.socialmediakotlin.R

class Alerts {
    companion object {
        fun warning(
            title: String,
            message: String,
            context: Context,
            completion: (() -> Unit)? = null
        ) {
            val builder =
                AlertDialog.Builder(ContextThemeWrapper(context, R.style.AlertDialogTheme))
            builder.setTitle(title)
            builder.setMessage(message)
            builder.setCancelable(false)
            builder.setPositiveButton(Constants.OK) { dialog, which ->
                if (completion == null) {
                    return@setPositiveButton
                }
                completion()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }
}