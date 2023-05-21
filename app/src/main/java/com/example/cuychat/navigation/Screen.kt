package com.example.cuychat.navigation

sealed class Screen(val route: String) {
    object Message : Screen("message")
    object Call : Screen("call")
    object Camera : Screen("camera")
    object Settings : Screen("settings")
    object Detail:Screen("detail")
    object Login:Screen("login")
    object Register:Screen("register")
}