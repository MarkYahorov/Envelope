package com.example.envelope.presentation.chat.models

data class ChatDetails(
    val id: Int,
    val name: String,
    val messages: List<String>,
    val image: String
)
