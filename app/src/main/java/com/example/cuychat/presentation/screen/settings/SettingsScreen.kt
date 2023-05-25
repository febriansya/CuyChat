package com.example.cuychat.presentation.screen.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.cuychat.navigation.Screen


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    logout: () -> Unit
) {


    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val (textLogin, kimak) = createRefs()
        TextButton(modifier = Modifier.constrainAs(textLogin) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
            top.linkTo(parent.top)
        },

            onClick = {
                logout()
            }) {
            Text(text = "logout")
        }
    }


}