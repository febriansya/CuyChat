package com.example.cuychat.presentation.screen.register

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.isPopupLayout
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.cuychat.R
import com.example.cuychat.common.UiState
import com.example.cuychat.navigation.Screen
import com.example.cuychat.ui.theme.Black
import com.example.cuychat.ui.theme.DarkGrey
import com.example.cuychat.ui.theme.TextTypografy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel,
    navController: NavController
) {

    var isButtonTouched by remember { mutableStateOf(false) }


    var usernameText by remember { mutableStateOf(TextFieldValue("")) }
    var passwordText by remember { mutableStateOf(TextFieldValue("")) }
    var emailText by remember { mutableStateOf(TextFieldValue("")) }

    var isLoading = false
    val uiState by registerViewModel.uiState
    val state = registerViewModel.uiStater


    var snackbarVisible by remember { mutableStateOf(false) }
    var snackbarSuccess by remember { mutableStateOf(false) }
    var snackbarError by remember { mutableStateOf(false) }

    var snackbarMessage by remember { mutableStateOf("") }


    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
    ) {
        val (username, failure, column, bgBottom, bottomLogin, register, loadingCircular) = createRefs()
        Column(
            modifier = Modifier
                .constrainAs(column) {
                    top.linkTo(parent.top, margin = 30.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .wrapContentHeight()
        ) {

//            username
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 30.dp),
                value = usernameText,
                onValueChange = { usernameText = it },
                placeholder = {
                    Text(
                        "Name", style = TextTypografy.titleMedium.copy(
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = DarkGrey,
                    textColor = Color.White,
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(38.dp))

//            password
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 30.dp),
                value = passwordText,
                onValueChange = { passwordText = it },
                placeholder = {
                    Text(
                        "Password", style = TextTypografy.titleMedium.copy(
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = DarkGrey,
                    textColor = Color.White,
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(38.dp))
//            password
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 30.dp),
                value = emailText,
                onValueChange = { emailText = it },
                placeholder = {
                    Text(
                        "Email", style = TextTypografy.titleMedium.copy(
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = DarkGrey,
                    textColor = Color.White,
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        }

        Button(
            onClick = {
                val username = usernameText.text.trim()
                val password = passwordText.text.trim()
                val email = emailText.text.trim()
                if (username.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty()) {
                    registerViewModel.viewModelScope.launch(Dispatchers.IO) {
                        registerViewModel.registerUser(
                            usernameText.text,
                            emailText.text,
                            passwordText.text
                        )
                        isButtonTouched = true
                    }
                } else {
                    snackbarMessage = "Please complete the all input field"
                    snackbarVisible = true
                }


            },
            modifier = Modifier.constrainAs(bottomLogin) {
                top.linkTo(column.bottom, margin = 12.dp)
                start.linkTo(column.start)
                end.linkTo(column.end)
            }

        ) {
            Text(
                modifier = Modifier.padding(start = 12.dp, end = 12.dp),
                text = "Register",
                style = TextTypografy.bodySmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }

        TextButton(
            onClick = {
                navController.navigate(Screen.Login.route)
            },
            modifier = Modifier.constrainAs(register) {
                start.linkTo(bottomLogin.end)
                end.linkTo(column.end)
                top.linkTo(bottomLogin.top)
                bottom.linkTo(bottomLogin.bottom)
            }
        ) {
            Text(
                text = "Login?", style = TextTypografy.bodySmall.copy(
                    color = Color.White
                )
            )
        }
        Image(
            modifier = Modifier
                .constrainAs(bgBottom) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(column.bottom)
                }
                .scale(3f, 2.5f),
            painter = painterResource(id = R.drawable.bg_gradient_login),
            contentDescription = "background"
        )

        if (isButtonTouched) {
            when (state) {
                is UiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.constrainAs(loadingCircular) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                    )
                }

                is UiState.Success -> {
                    if (!snackbarSuccess) {
                        Snackbar(
                            action = {
                                TextButton(onClick = { snackbarSuccess = true }) {
                                    Text(text = "Dismiss")
                                }
                            }
                        ) {
                            Text(text = "succcess register")
                        }
                    }

                    navController.navigate(Screen.Login.route) {
                        launchSingleTop = true
                    }
                }

                is UiState.Failure -> {
                    if (!snackbarError) {
                        Snackbar(
                            action = {
                                TextButton(onClick = { snackbarError = true }) {
                                    Text(text = "Dismiss")
                                }
                            }
                        ) {
                            Text(text = "failed register")
                        }
                    }

                }
            }
        }
    }
    if (snackbarVisible) {
        Snackbar(
            action = {
                // Optionally, you can add an action to the Snackbar
                TextButton(onClick = { snackbarVisible = false }) {
                    Text(text = "Dismiss")
                }
            }
        ) {
            Text(text = snackbarMessage)
        }
    }
}