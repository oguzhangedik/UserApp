package com.example.userapp.core.extensions

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.toastMessage(message: String, duration: Int = Toast.LENGTH_SHORT) =
    requireContext().toastMessage(message = message, duration = duration)


fun Fragment.toastMessage(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT) =
    requireContext().toastMessage(message = message, duration = duration)

fun Context.toastMessage(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Context.toastMessage(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, getString(message), duration).show()
}
