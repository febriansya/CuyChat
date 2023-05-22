package com.example.cuychat.common

sealed class UiState {
    object Loading : UiState()
    data class Success(val data: Any) : UiState()
    data class Failure(val error: String?) : UiState()
}
