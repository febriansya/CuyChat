package com.example.cuychat.data.repository

import com.example.cuychat.common.UiState
import com.example.cuychat.domain.model.UserProfile
import com.example.cuychat.domain.repository.ChatRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase
) : ChatRepository {
    override suspend fun register(
        username: String,
        email: String,
        password: String,
        uiState: (UiState) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            uiState.invoke(UiState.Loading)
            if (task.isSuccessful) {
                uiState.invoke(UiState.Success("Register Successfully"))
                val user = auth.currentUser?.uid
                val userRef = database.reference.child("users").child(user.toString())
                val userPofile = UserProfile(
                    user.toString(),
                    username = username,
                )
                userRef.setValue(userPofile)
            } else {
                uiState.invoke(UiState.Failure("Register Failed"))
            }
        }
    }

    override fun login(email: String, password: String, uiState: (UiState) -> Unit) {
        uiState.invoke(UiState.Loading)
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    uiState.invoke(UiState.Success("Login Berhasil"))
                } else {
                    uiState.invoke(UiState.Failure("Email or Password wronng"))
                }
            }.addOnFailureListener {
                uiState.invoke(UiState.Failure("Gagal Login"))
            }
    }
}


