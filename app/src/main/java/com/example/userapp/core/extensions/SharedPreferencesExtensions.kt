package com.example.userapp.core.extensions

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> SharedPreferences.get(key: String, s: String): T? {
    if (!this.contains(key)) {
        return null
    }

    return when (T::class) {
        Boolean::class -> this.getBoolean(key, false) as T
        Float::class -> this.getFloat(key, 0f) as T
        Int::class -> this.getInt(key, 0) as T
        Long::class -> this.getLong(key, 0) as T
        String::class -> this.getString(key, null) as T
        else -> {
            Gson().fromJson(getString(key, ""), object : TypeToken<T>() {}.type)
        }
    }
}

inline fun <reified T> SharedPreferences.set(key: String, value: T?) {
    val editor = this.edit()

    if (value == null) {
        editor.remove(key)
        editor.apply()
    } else {
        when (T::class) {
            Boolean::class -> editor.putBoolean(key, value as Boolean)
            Float::class -> editor.putFloat(key, value as Float)
            Int::class -> editor.putInt(key, value as Int)
            Long::class -> editor.putLong(key, value as Long)
            String::class -> editor.putString(key, value as String)
            else -> {
                editor.putString(key, Gson().toJson(value))
            }
        }
        editor.apply()
    }
}
