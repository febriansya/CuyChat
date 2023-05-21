package com.example.cuychat.domain.usecase

import javax.inject.Inject


data class MainUseCase @Inject constructor(
    val registerUseCase: RegisterUseCase
)
