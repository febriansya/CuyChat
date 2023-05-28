package com.example.cuychat.presentation.screen.register

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cuychat.common.UiState
import com.example.cuychat.domain.repository.ChatRepository
import com.example.cuychat.domain.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    val mainUseCase: MainUseCase,
    val repository: ChatRepository
) : ViewModel() {

    val uiState: MutableState<UiState?> = mutableStateOf(null)

    var uiStater by mutableStateOf<UiState>(UiState.Loading)
        private set
    fun registerUser(username: String, email: String, password: String) {
        viewModelScope.launch {
            repository.register(username, email, password) {
                uiState.value = it
                uiStater = it
            }
        }
    }
}




