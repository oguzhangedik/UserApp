package com.example.userapp.core.extensions

import android.app.AlertDialog
import android.content.Context
import androidx.core.content.ContextCompat
import com.example.userapp.R

fun Context.showDialog(
    cancelable: Boolean = false,
    cancelableTouchOutside: Boolean = false,
    builderFunction: AlertDialog.Builder.() -> Any
) {
    val builder = AlertDialog.Builder(this)
    builder.builderFunction()
    val dialog = builder.create()

    dialog.setCancelable(cancelable)
    dialog.setCanceledOnTouchOutside(cancelableTouchOutside)

    dialog.setOnShowListener {
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isAllCaps = false
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).isAllCaps = false
    }

    dialog.show()
    dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        .setTextColor(ContextCompat.getColor(this, R.color.black))
    dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
        .setTextColor(ContextCompat.getColor(this, R.color.black))
}

fun AlertDialog.Builder.positiveButton(
    text: String = context.getString(R.string.ok_text),
    handleClick: (i: Int) -> Unit = {}
) {
    this.setPositiveButton(text) { _, i -> handleClick(i) }
}

fun AlertDialog.Builder.negativeButton(
    text: String = context.getString(R.string.cancel_text),
    handleClick: (i: Int) -> Unit = {}
) {
    this.setNegativeButton(text) { _, i -> handleClick(i) }
}

fun AlertDialog.Builder.neutralButton(text: String, handleClick: (i: Int) -> Unit = {}) {
    this.setNeutralButton(text) { _, i -> handleClick(i) }
}
