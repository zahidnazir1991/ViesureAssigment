package com.viesure.assigment.listitems

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.viesure.assigment.R
import com.viesure.assigment.models.BookDetailsResponseItem
import com.viesure.assigment.util.scalableFontSize
import com.viesure.assigment.util.sdp

@Composable
fun RecyclerViewSingleItem(item: BookDetailsResponseItem, callback: (BookDetailsResponseItem) -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White, //Card background color
            contentColor = Color.White  //Card content color,e.g.text
        ),
        shape = RoundedCornerShape(sdp(6.dp)),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .testTag("bookItem")
            .height(sdp(baseSizeDp = 68.dp))
            .clickable {
                callback(item)
            }
    ) {


        Row {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(.9f)
                    .fillMaxHeight()
            ) {
                val painter = rememberAsyncImagePainter(item.image)
                Image(
                    painter = painter,
                    contentDescription = "",
                    modifier = Modifier
                        .width(sdp(baseSizeDp = 50.dp))
                        .height(sdp(baseSizeDp = 50.dp))
                        .clip(RoundedCornerShape(30.dp))  // Increase the corner size for more rounded effect
                        .border(0.dp, Color.Transparent),
                    contentScale = ContentScale.Crop
                )

            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(4f)
                    .padding(start = sdp(baseSizeDp = 8.dp), end = sdp(baseSizeDp = 8.dp))
            ) {
                Text(
                    text = item.title ?: "",
                    style = TextStyle(
                        fontSize = scalableFontSize(baseSizeSp = 15.sp),
                        lineHeight = scalableFontSize(baseSizeSp = 21.sp),
                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF231F20),
                        letterSpacing = 0.5.sp,
                    ),

                    )

                Text(
                    text = item.description ?: "",
                    style = TextStyle(
                        fontSize = scalableFontSize(baseSizeSp = 11.sp),
                        lineHeight = scalableFontSize(baseSizeSp = 15.sp),
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        fontWeight = FontWeight(300),
                        color = Color(0xFF494949),
                    ), maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = sdp(baseSizeDp = 4.dp))
                )
            }
        }
    }
}