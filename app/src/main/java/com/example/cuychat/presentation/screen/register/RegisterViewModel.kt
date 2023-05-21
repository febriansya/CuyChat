package com.example.cuychat.presentation.screen.register

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.cuychat.common.UiState
import com.example.cuychat.domain.repository.ChatRepository
import com.example.cuychat.domain.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    val mainUseCase: MainUseCase,
    val repository: ChatRepository
) : ViewModel() {


    fun registerUser(username: String, email: String, password: String) {

    }
}




