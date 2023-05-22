package com.example.cuychat.data.repository

import android.util.Log
import com.example.cuychat.common.UiState
import com.example.cuychat.data.source.remote.dto.User
import com.example.cuychat.domain.repository.ChatRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
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
//        try {
//            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
//            Log.d("register", "register success")
//            val userId = authResult.user?.uid
//            if (userId != null) {
//                val userRef = database.reference.child("users").child(userId)
//                val user = User(username, email)
//                userRef.setValue(user).await()
//                Log.d("register", "register success")
//            } else {
//                Log.d("register", "register failed")
//            }
//        } catch (exception: Exception) {
//            Log.d("exception", exception.toString())
//        }

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                uiState.invoke(UiState.Success("Register Successfully"))
            }else{
                uiState.invoke(UiState.Success("Email not verified"))
            }
        }.addOnFailureListener {
            uiState.invoke(UiState.Failure("Failed Register"))
        }

    }
}
