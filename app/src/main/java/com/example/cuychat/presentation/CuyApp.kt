package com.example.cuychat.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.cuychat.R
import com.example.cuychat.navigation.NavigationItem
import com.example.cuychat.navigation.Screen
import com.example.cuychat.presentation.screen.call.CallScren
import com.example.cuychat.presentation.screen.camera.CameraScreen
import com.example.cuychat.presentation.screen.chat.DetailChatScreen
import com.example.cuychat.presentation.screen.chat.HomeScreen
import com.example.cuychat.presentation.screen.login.LoginScreen
import com.example.cuychat.presentation.screen.login.LoginViewModel
import com.example.cuychat.presentation.screen.register.RegisterScreen
import com.example.cuychat.presentation.screen.register.RegisterViewModel
import com.example.cuychat.presentation.screen.settings.SettingsScreen
import com.example.cuychat.ui.theme.DarkGrey
import com.example.cuychat.ui.theme.Purple
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuyApp(
    navHostController: NavHostController = rememberNavController(),
    registerViewModel: RegisterViewModel = hiltViewModel(),
    loginViewModel: LoginViewModel = hiltViewModel(),
    auth: FirebaseAuth = FirebaseAuth.getInstance(),
) {


    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(bottomBar = {
        if (currentRoute != Screen.Detail.route && currentRoute != Screen.Register.route && currentRoute != Screen.Login.route) {
            BottomBar(navHostController)
        }
    }) {
        NavHost(
            navController = navHostController,
            startDestination = Screen.Login.route,
        ) {
            composable(Screen.Message.route) {
                HomeScreen(navHostController)
            }
            composable(Screen.Call.route) {
                CallScren()
            }

            composable(Screen.Camera.route) {
                CameraScreen()
            }

            composable(Screen.Settings.route) {
                SettingsScreen(navHostController) {
                    val current = auth.currentUser
                    if (current != null) {
                        auth.signOut()
                        navHostController.navigate(Screen.Login.route) {
                            launchSingleTop = true
                            popUpTo(Screen.Login.route) {
                                inclusive = true
                            }
                            popUpTo(Screen.Message.route) {
                                inclusive = true
                            }
                        }
                    }
                }
            }
            composable(Screen.Detail.route) {
                DetailChatScreen()
//                LoginScreen(navHostController)
            }
            composable(Screen.Register.route) {
                RegisterScreen(registerViewModel, navHostController)
            }

            composable(Screen.Login.route) {
//                if user have login preveus
                if (auth.currentUser != null) {
                    navHostController.navigate(Screen.Message.route) {
                        launchSingleTop = true
                        popUpTo(Screen.Login.route) {
                            inclusive = true
                        }
                    }
                } else {
                    LoginScreen(
                        navController = navHostController, loginViewModel = loginViewModel
                    ) {
                        navHostController.navigate(Screen.Message.route) {
                            launchSingleTop = true
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .wrapContentHeight()
            .clip(
                RoundedCornerShape(
                    topStart = 20.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 20.dp
                )
            )
            .background(color = DarkGrey)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route


        val navigationItems = listOf(
            NavigationItem(
                title = "message", icon = R.drawable.ic_chat, screen = Screen.Message
            ),

            NavigationItem(
                title = "call", icon = R.drawable.ic_call, screen = Screen.Call
            ),


            NavigationItem(
                title = "camera", icon = R.drawable.ic_camera, screen = Screen.Camera
            ),


            NavigationItem(
                title = "settings", icon = R.drawable.ic_setting, screen = Screen.Settings
            ),
        )

        BottomNavigation(
            backgroundColor = DarkGrey,
        ) {
            navigationItems.map { item ->
                BottomNavigationItem(
                    selectedContentColor = Purple,
                    unselectedContentColor = DarkGrey,

//                    ini harus sesuai dengan yang di click berdasarkan screen route
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Image(
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            colorFilter = if (currentRoute == item.screen.route) {
                                ColorFilter.tint(Purple)
                            } else {
                                null
                            }
                        )
                    },
                )
            }
        }
    }
}



