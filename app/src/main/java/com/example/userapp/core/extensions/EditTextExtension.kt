package com.example.userapp.core.extensions

import android.text.Editable
import android.text.InputFilter
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import com.example.userapp.core.common.MaskWatcher
import com.google.android.material.textfield.TextInputEditText

fun EditText.applyNumberFilter() {
    this.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(13))
    this.addTextChangedListener(MaskWatcher("### ### ## ##"))
}

fun TextInputEditText.applyNumberFilter() {
    this.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(13))
    this.addTextChangedListener(MaskWatcher("### ### ## ##"))
}

fun TextInputEditText.applyNumberFilterWithPrefix(lengthSize: Int) {
    this.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(lengthSize))
    this.addTextChangedListener { text: Editable? ->
        if (text.toString() == "0") {
            setText("")
        }
        if (text.toString() == "9") {
            setText("")
        }
        if (text.toString() == "+") {
            setText("")
        }
    }
    this.addTextChangedListener(MaskWatcher("### ### ## ##"))
}
fun EditText.applyNumberFilterWithPrefixMaskThree() {
    this.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(9))
    this.addTextChangedListener { text: Editable? ->
        if (text.toString() == "+") {
            setText("")
        }
    }
    this.addTextChangedListener(MaskWatcher("### ## ##"))
}
fun EditText.applyNumberFilterWithPrefix(lengthSize: Int) {
    this.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(lengthSize))
    this.addTextChangedListener { text: Editable? ->
        if (text.toString() == "0") {
            setText("")
        }
        if (text.toString() == "9") {
            setText("")
        }
        if (text.toString() == "+") {
            setText("")
        }
    }
    this.addTextChangedListener(MaskWatcher("### ### ## ##"))
}

fun AppCompatEditText.applyYearFilter() {
    this.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(5))
    this.addTextChangedListener(MaskWatcher("##/##"))
}
