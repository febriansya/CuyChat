package com.example.cuychat.common

sealed class Resources<T>(val data: Any? = null, val message: String? = null) {
    class Success<T>(data: Any?,message: String?=null) : Resources<T>(data,message)
    class Error<T : Any>(message: String?, data: Any? = null) : Resources<T>(data, message)
    class Loading<T>(data: Any? = null) : Resources<T>(data)
}