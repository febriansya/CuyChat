package com.example.cuychat.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cuychat.R
import com.example.cuychat.ui.theme.Purple
import com.example.cuychat.ui.theme.TextTypografy

@Composable
fun CurrentActive() {
    val image = arrayListOf<Int>(
        R.drawable.img1,
        R.drawable.img2,
        R.drawable.img3,
        R.drawable.img4
    )
    val name = arrayListOf<String>(
        "Isabelle",
        "Ethan",
        "Harper",
        "Alexander"
    )

    Column(
        modifier = Modifier.fillMaxWidth(),

        ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .wrapContentWidth()
                .background(Purple)
                .padding(6.dp)

        ) {
            Text(text = "Currently Active ", style = TextTypografy.bodyMedium, color = Color.White)
            Image(
                modifier = Modifier
                    .width(7.62f.dp)
                    .height(7.62f.dp),
                painter = painterResource(id = R.drawable.ic_active),
                contentDescription = "icon active"
            )
        }
        Spacer(modifier = Modifier.height(13.dp))
        LazyRow() {
            items(image.size) { index ->
                ItemUser(image[index], name[index])
            }
        }
    }
}


@Composable
fun ItemUser(
    image: Int,
    name: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 9.dp)
    ) {
        Image(painter = painterResource(id = image), contentDescription = "tes")
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = name, style = TextTypografy.bodySmall.copy(
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}


@Preview(showBackground = true, backgroundColor = 0XFF161616)
@Composable
fun Current() {
    CurrentActive()
}