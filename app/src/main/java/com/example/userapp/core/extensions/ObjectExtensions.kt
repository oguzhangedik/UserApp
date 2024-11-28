package com.example.userapp.core.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Any.cloneObject(): T = Gson().fromJson(
    Gson().toJson(
        this
    ),
    object : TypeToken<T>() {}.type
)

inline fun <reified T> Gson.convertToObject(json: String): T =
    fromJson(json, object : TypeToken<T>() {}.type)

/**
 * Control if +sending object is equal to second @secondObject
 */
fun Any.isEqualWith(secondObject: Any): Boolean =
    Gson().toJson(this).equals(
        Gson().toJson(
            secondObject
        )
    )
