package com.example.userapp.core.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.userapp.core.base.viewmodel.Event

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, observer: (T) -> Unit) {
    liveData.observe(
        this
    ) {
        it?.let { t -> observer(t) }
    }
}

fun <T> LifecycleOwner.observe(liveData: MutableLiveData<T>, observer: (T) -> Unit) {
    liveData.observe(
        this
    ) {
        it?.let { t -> observer(t) }
    }
}

fun <T> LifecycleOwner.observeEvent(liveData: LiveData<Event<T>>, observer: (T) -> Unit) {
    liveData.observe(
        this
    ) {
        it?.getContentIfNotHandled()?.let { t -> observer(t) }
    }
}
