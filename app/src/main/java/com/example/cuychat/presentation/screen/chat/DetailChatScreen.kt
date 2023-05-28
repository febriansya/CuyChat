package com.example.cuychat.presentation.screen.chat

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cuychat.R
import com.example.cuychat.ui.theme.Black
import com.example.cuychat.ui.theme.DarkGrey
import com.example.cuychat.ui.theme.GreenText
import com.example.cuychat.ui.theme.TextTypografy
import com.example.cuychat.ui.theme.WhiteGrey

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailChatScreen() {
    var chat by remember {
        mutableStateOf("")
    }
    Scaffold(
        topBar = { TopBar() },
        containerColor = Black,
        bottomBar = {
            BottomAppBar(
                containerColor = DarkGrey,
            ) {

                TextField(
                    trailingIcon = {
                        IconButton(
                            onClick = {

                            }) {
                            Icon(
                                Icons.Filled.Send,
                                contentDescription = null
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = chat, onValueChange = {
                        chat = it
                    }

                )
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it)

        ) {
            Row(Modifier.fillMaxWidth()) {
                ItemSendChat(isFromMe = false, message = "Hello what your name?")
            }
            Row(
                Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement . End
            ) {
                ItemSendChat(isFromMe =true , message = "i am yan, whats your?")
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        modifier = Modifier.height(112.dp),
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = DarkGrey),
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    modifier = Modifier
                        .width(67.dp)
                        .height(67.dp)
                        .clip(CircleShape),
                    painter = painterResource(id = R.drawable.img3),
                    contentDescription = ""
                )
                Column(
                    Modifier
                        .weight(1f)
                        .padding(start = 12.dp)
                ) {
                    Text(
                        text = "Harper", style = TextTypografy.titleSmall.copy(
                            color = WhiteGrey
                        )
                    )
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_active),
                            contentDescription = ""
                        )
                        Text(
                            modifier = Modifier.padding(start = 5.dp),
                            text = "Active", style = TextTypografy.bodySmall.copy(
                                color = GreenText
                            )
                        )
                    }
                }
                Image(
                    modifier = Modifier
                        .clickable { }
                        .indication(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple()
                        ),
                    painter = painterResource(id = R.drawable.ic_call),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(25.dp))
                Image(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable { },
                    painter = painterResource(id = R.drawable.ic_camera),
                    contentDescription = ""
                )
            }
        })
}


@Composable
fun ItemSendChat(isFromMe: Boolean, message: String) {
    Row(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = if (isFromMe) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            color = if (isFromMe) Color(0xFFB2DFDB) else Color(0xFFE0E0E0),
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 4.dp,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = message,
                modifier = Modifier.padding(8.dp),
                color = if (isFromMe) Color.Black else Color.Black,
                style = TextStyle(fontSize = 16.sp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ItemSend() {
    ItemSendChat(true, "hallo ")
}


@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailChatScreen()
}