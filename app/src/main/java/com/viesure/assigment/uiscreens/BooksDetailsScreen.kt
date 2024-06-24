package com.viesure.assigment.uiscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.viesure.assigment.R
import com.viesure.assigment.customview.CustomText
import com.viesure.assigment.models.BookDetailsResponseItem
import com.viesure.assigment.util.formatDate
import com.viesure.assigment.util.scalableFontSize
import com.viesure.assigment.util.sdp
import com.viesure.assigment.viewmodels.BookDetailViewModel

@Composable
fun BookDetailsScreen(item: BookDetailsResponseItem) {
    val viewModel: BookDetailViewModel = hiltViewModel()
    Column(
        modifier = Modifier
            .background(color = Color(0xFFF1F1F1))
            .fillMaxSize()
            .padding(
                top = sdp(baseSizeDp = 8.dp),
                bottom = sdp(baseSizeDp = 8.dp),
                start = sdp(baseSizeDp = 12.dp),
                end = sdp(baseSizeDp = 12.dp)
            )
            .verticalScroll(rememberScrollState()),
    ) {
        val painter = rememberAsyncImagePainter(item.image)
        Image(
            painter = painter,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(sdp(baseSizeDp = 200.dp))
                .border(0.dp, Color.Transparent),
            contentScale = ContentScale.Crop
        )


        CustomText(
            text = item.title ?: "",
            modifier = Modifier.padding(top = sdp(baseSizeDp = 8.dp)).fillMaxWidth(),
            15,
            21,
            FontFamily(Font(R.font.roboto_medium)),
            FontWeight.Medium,
            Color(0xFF231F20),
            2,
             0.5.sp
        )


        CustomText(
            text = formatDate(item.release_date ?: ""),
            modifier =  Modifier
                .padding(top = sdp(baseSizeDp = 4.dp))
                .align(Alignment.End),
            12,
            18,
            FontFamily(Font(R.font.roboto_regular)),
            FontWeight.Normal,
            Color.Black,
            1,
            0.5.sp,

        )


        CustomText(
            text = item.description ?: "",
            modifier = Modifier
                .padding(top = sdp(baseSizeDp = 8.dp))
                .wrapContentHeight(),
            12,
            15,
            FontFamily(Font(R.font.roboto_regular)),
            FontWeight.Light,
            Color(0xFF494949),
           letterSpacing =  0.5.sp
        )

        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(
                top = sdp(
                    baseSizeDp = 8.dp
                ),
                bottom = sdp(baseSizeDp = 10.dp)
            )
        ) {


            CustomText(
                text = "Author :",
                modifier = Modifier.wrapContentWidth(),
                12,
                18,
                FontFamily(Font(R.font.roboto_medium)),
                FontWeight.Medium,
                color = Color(0xFF231F20),
                letterSpacing =  0.5.sp,
                maxLines = 1
            )


            CustomText(
                text = item.author ?: "",
                modifier = Modifier.wrapContentWidth(),
                12,
                18,
                FontFamily(Font(R.font.roboto_regular)),
                FontWeight.Normal,
                color = Color.DarkGray,
                letterSpacing =  0.5.sp,
                maxLines = 1
            )
        }
    }
}