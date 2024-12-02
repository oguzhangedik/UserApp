package com.example.userapp.core.extensions

import com.google.gson.Gson
import timber.log.Timber

object JsonSerializer {
    /**
     * Converting Json String to Object
     */
    inline fun <reified T> String.toObject(): T? = try {
        when (T::class) {
            Boolean::class -> toBoolean() as T
            Float::class -> toFloat() as T
            Int::class -> toInt() as T
            Long::class -> toLong() as T
            Double::class -> toDouble() as T
            String::class -> toString() as T
            else -> {
                Gson().fromJson(this, T::class.java)
            }
        }
    } catch (e: Exception) {
        Timber.e(e)
        null
    }
}
