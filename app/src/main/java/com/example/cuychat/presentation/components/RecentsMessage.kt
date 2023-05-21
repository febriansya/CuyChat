package com.example.cuychat.presentation.components

import android.R.attr.visible
import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.cuychat.R
import com.example.cuychat.navigation.Screen
import com.example.cuychat.ui.theme.DarkGrey
import com.example.cuychat.ui.theme.Purple
import com.example.cuychat.ui.theme.TextTypografy


@Composable
fun RecentMessage(
    navController: NavController
) {


    val image = arrayListOf<Int>(
        R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4
    )
    val name = arrayListOf<String>(
        "Isabelle", "Ethan", "Harper", "Alexander"
    )

    val pesan = arrayListOf<String>(
        "Hay", "Hello", "Assalamualaikum", "Terimakasih"
    )

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(bottom = 23.dp)
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
            Text(text = "Recents ", style = TextTypografy.bodyMedium, color = Color.White)
            Image(
                modifier = Modifier
                    .width(7.62f.dp)
                    .height(7.62f.dp),
                painter = painterResource(id = R.drawable.ic_time),
                contentDescription = "time"
            )
        }

        Spacer(modifier = Modifier.height(18.dp))
        LazyColumn(
            modifier = Modifier.padding(bottom = 30.dp)
        ) {
            items(image.size) { pesann ->
                ItemRecentMessage(image[pesann], name[pesann], pesan[pesann]) {
                    Log.d("tes", Screen.Detail.route)
                    navController.navigate(Screen.Detail.route)
                }
            }
        }
    }
}

@Composable
fun ItemRecentMessage(image: Int, nama: String, pesan: String, onClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = DarkGrey),
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp)
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Image(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 10.dp),
                painter = painterResource(id = image),
                contentDescription = ""
            )
            Column(
                Modifier.weight(3f), verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = nama, style = TextTypografy.bodyMedium.copy(
                        color = Color.White, fontWeight = FontWeight.Medium
                    )
                )
                Text(
                    text = pesan, style = TextTypografy.titleSmall.copy(
                        color = Color.White
                    ), overflow = TextOverflow.Ellipsis, maxLines = 1, fontSize = 14.sp
                )
            }
            Column(
                Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = "4 Min",
                    style = TextTypografy.titleSmall.copy(
                        color = Purple, fontSize = 12.sp
                    ),
                )
                Text(
                    modifier = Modifier
                        .padding(20.dp)
                        .drawBehind {
                            drawCircle(
                                color = Purple, radius = this.size.maxDimension / 2
                            )
                        }, text = "1", style = TextTypografy.titleSmall.copy(
                        color = Color.White, background = Purple, fontSize = 18.sp
                    )
                )
            }
        }
    }
}

//@Preview
//@Composable
//fun RecentPreview() {
//    RecentMessage()
//}