package com.example.userapp.core.extensions

import android.view.View

fun View.delayClick(){
    isClickable = false
    postDelayed({ isClickable = true },1000)
}
