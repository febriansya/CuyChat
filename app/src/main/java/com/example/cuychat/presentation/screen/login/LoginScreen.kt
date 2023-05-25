package com.example.cuychat.presentation.screen.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.cuychat.R
import com.example.cuychat.common.UiState
import com.example.cuychat.navigation.Screen
import com.example.cuychat.ui.theme.Black
import com.example.cuychat.ui.theme.DarkGrey
import com.example.cuychat.ui.theme.TextTypografy
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    navController: NavController,
    navigateToHome: () -> Unit
) {
    var isButtonTouched by remember { mutableStateOf(false) }

    val state = loginViewModel.uiState

    var usernameText by remember { mutableStateOf(TextFieldValue("")) }
    var passwordText by remember { mutableStateOf(TextFieldValue("")) }

    var snackbarVisible by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }

    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }



    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
    ) {
        val (username, password, column, bgBottom, bottomLogin, register, loadingCircular) = createRefs()
        Column(
            modifier = Modifier
                .constrainAs(column) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
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

            Spacer(modifier = Modifier.height(38.dp))

//            password
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 30.dp),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Please provide localized description for accessibility services
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, description)
                    }
                },
                value = passwordText,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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
        }

        Button(
            onClick = {
                val username = usernameText.text.trim()
                val password = passwordText.text.trim()
                if (username.isNotEmpty() && password.isNotEmpty()) {
                    loginViewModel.viewModelScope.launch(Dispatchers.IO) {
                        loginViewModel.signIn(usernameText.text, passwordText.text)
                        isButtonTouched = true
                    }
                } else {
                    snackbarMessage = "Please complete the input field"
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
                text = "Login", style = TextTypografy.bodySmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }


        TextButton(
            onClick = {
                navController.navigate(Screen.Register.route)
            },
            modifier = Modifier.constrainAs(register) {
                start.linkTo(bottomLogin.end)
                end.linkTo(column.end)
                top.linkTo(bottomLogin.top)
                bottom.linkTo(bottomLogin.bottom)
            }
        ) {
            Text(
                text = "Register?", style = TextTypografy.bodySmall.copy(
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
                .scale(3f, 2f),
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
                    Snackbar() {
                        Text(text = "succcess Login")
                    }
                    navigateToHome()
                }

                is UiState.Failure -> {
                    Snackbar() {
                        Text(text = "failed Login check your email password")
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