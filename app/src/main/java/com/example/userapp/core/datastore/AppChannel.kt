package com.example.userapp.core.datastore

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

object AppChannel {
    private val _channel = Channel<ChannelData>()
    val channel = _channel.receiveAsFlow()

    suspend fun sendData(data: ChannelData) {
        _channel.send(data)
    }
}

sealed class ChannelData(key: String) {
    data class StringData(val key : String, val value: String?) : ChannelData(key)
    data class IntData(val key: String, val value: Int?) : ChannelData(key)
    data class IdentityNotFound(val key: String) : ChannelData(key)
}
