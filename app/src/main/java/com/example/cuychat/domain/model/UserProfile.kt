package com.example.cuychat.domain.model

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date


data class UserProfile(
    val uuid: String?,
    @ServerTimestamp
    val updateAt: Date? = null,
    var username: String? = "",
)
