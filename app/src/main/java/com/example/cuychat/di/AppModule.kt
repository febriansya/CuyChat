package com.example.cuychat.di

import com.example.cuychat.data.repository.ChatRepositoryImpl
import com.example.cuychat.domain.repository.ChatRepository
import com.example.cuychat.domain.usecase.MainUseCase
import com.example.cuychat.domain.usecase.RegisterUseCase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideDatabaseReference() = FirebaseDatabase.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseAuthInstance() = FirebaseAuth.getInstance()


    @Provides
    @Singleton
    fun provideChatRepository(
        firebaseAuth: FirebaseAuth,
        firebaseDatabase: FirebaseDatabase
    ): ChatRepository {
        return ChatRepositoryImpl(firebaseAuth, firebaseDatabase)
    }

    @Provides
    @Singleton
    fun provideMainUseCases(repository: ChatRepository): MainUseCase {
        return MainUseCase(RegisterUseCase(repository))
    }


}