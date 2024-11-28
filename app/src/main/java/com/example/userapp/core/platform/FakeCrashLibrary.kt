package com.example.userapp.core.platform

import timber.log.Timber

class FakeCrashLibrary private constructor() {
    companion object {
        fun log(priority: Int, tag: String?, message: String?) {
            if (message != null) {
                if (tag != null) {
                    Timber.tag(tag).v(message)
                }
            }
        }

        fun logWarning(t: Throwable?) {
            println(t)
        }

        fun logError(t: Throwable?) {
            println(t)
        }
    }

    init {
        throw AssertionError("No instances.")
    }
}
