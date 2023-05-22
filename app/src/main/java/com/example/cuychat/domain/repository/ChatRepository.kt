package com.example.cuychat.domain.repository

import com.example.cuychat.common.UiState

interface ChatRepository {
    suspend fun register(
        username: String,
        email: String,
        password: String,
        uiState: (UiState) -> Unit
    )
}