package com.example.userapp.core.common

import android.os.CountDownTimer

class MyTimer(val millisInFuture: Long, val countDownInterval: Long = 1) {
    private var timer: CountDownTimer? = null
    private var timerLifecycle: Boolean = false

    fun startTimer(onFinish: () -> Unit, onTick: (millisUntilFinished: Long) -> Unit, timerType: TimerType, timerLifecycle: Boolean? = true, oneTask: Boolean = false) {
        if (timerLifecycle != null) {
            this.timerLifecycle = timerLifecycle
        }
        if (oneTask) {
            timer?.cancel()
            timer = null
        }
        timer = object : CountDownTimer(millisInFuture, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                onTick(millisUntilFinished)
            }
            override fun onFinish() {
                onFinish()
                if (timerType == TimerType.LoopTimer) {
                    timer?.start()
                }
            }
        }
        timer?.start()
    }
    fun timerClose() {
        if (!timerLifecycle) {
            timer?.cancel()
            timer = null
        }
    }
    fun forceClose(){
        timer?.cancel()
        timer = null
    }
}
enum class TimerType {
    LoopTimer,
    OneTimeTimer
}
