package com.example.cuychat.presentation.screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cuychat.common.UiState
import com.example.cuychat.domain.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val repository: ChatRepository
) : ViewModel() {

    var uiState by mutableStateOf<UiState>(UiState.Loading)
        private set

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            repository.login(email, password) {
                uiState = it
            }
        }
    }
}