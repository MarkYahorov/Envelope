package com.example.envelope.presentation.chats.models

data class Chat(
    val id: Int,
    val title: String,
    val message: String,
    val image: String,
    val time: Long
)
