package com.example.cuychat.domain.repository

interface ChatRepository {
    suspend fun register(username:String,email:String,password:String)
}