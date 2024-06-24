package com.viesure.assigment.customview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.viesure.assigment.R
import com.viesure.assigment.util.scalableFontSize
import com.viesure.assigment.util.sdp


@Composable
fun CustomErrorScreen(subtitle: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.wrapContentHeight()
    ) {
        Text(
            text = subtitle,
            modifier = Modifier.padding(top = sdp(8.dp), start = sdp(16.dp),end = sdp(16.dp)),
            style = TextStyle(
                fontSize = scalableFontSize(baseSizeSp = 14.sp),
                lineHeight = scalableFontSize(baseSizeSp = 18.sp),
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF848484),
                textAlign = TextAlign.Center,
                letterSpacing = scalableFontSize(baseSizeSp = 0.25.sp),
            )
        )
    }


}