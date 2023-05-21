package com.example.cuychat.domain.usecase

import android.util.Log
import com.example.cuychat.common.Resources
import com.example.cuychat.data.source.remote.dto.User
import com.example.cuychat.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterUseCase(
    private val repository: ChatRepository
) {
    suspend fun registerUser(
        username: String,
        password: String,
        email: String
    ): Flow<Resources<User>> = flow {
        try {
            emit(Resources.Loading<User>())
            val user = repository.register(username, email, password)
            emit(Resources.Success<User>(user))
        } catch (exception: Exception) {
            emit(
                Resources.Error<User>(
                    exception.localizedMessage ?: ("An unexpected error")
                )
            )
            Log.d("usecase", exception.toString())
        }
    }
}