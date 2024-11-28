package com.example.userapp.core.common.logto.data

data class LogToConfigApp(
    val endpoint: String,
    val appId: String,
    val scopes: List<String>? = null,
    val resources: List<String>? = null,
    val usingPersistStorage: Boolean = true,
    val prompt: Prompt = Prompt.Consent
)
