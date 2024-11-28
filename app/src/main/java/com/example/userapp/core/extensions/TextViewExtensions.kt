package com.example.userapp.core.extensions

import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter

@BindingAdapter(
    value = ["app:htmlText"]
)
fun TextView.htmlText(
    htmlText: String?
) {
    this.text = HtmlCompat.fromHtml(
        htmlText.toString(),
        HtmlCompat.FROM_HTML_MODE_COMPACT
    )
}



