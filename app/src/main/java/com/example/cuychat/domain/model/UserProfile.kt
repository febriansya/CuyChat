package com.example.cuychat.domain.model

import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class UserProfile(
    val uuid: String,
    @ServerTimestamp
    val updateAt: Date? = null,
    var username: String = "",
)
