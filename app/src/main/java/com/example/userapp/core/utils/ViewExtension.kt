package com.example.userapp.core.utils

import android.view.View

fun View.delayClick(){
    isClickable = false
    postDelayed({ isClickable = true },1000)
}
