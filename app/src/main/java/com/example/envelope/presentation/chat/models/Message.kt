package com.example.envelope.presentation.chat.models

data class Message(
    val id: Int,
    val text: String,
    val isMyMessage: Boolean
)
