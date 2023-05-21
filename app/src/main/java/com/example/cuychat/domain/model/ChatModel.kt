package com.example.cuychat.domain.model

import com.example.cuychat.data.source.remote.dto.ChatMessage

data class ChatModel(
    val idUser: String,
    val pesan: ChatMessage,
    val status: Boolean
)
