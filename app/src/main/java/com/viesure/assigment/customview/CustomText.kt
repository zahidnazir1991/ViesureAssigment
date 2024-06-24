package com.viesure.assigment.customview

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.viesure.assigment.util.scalableFontSize


@Composable
fun CustomText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: Int = 14,
    lineHeight: Int = 20,
    fontFamily: FontFamily? = null,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = Color.Black,
    maxLines: Int = Int.MAX_VALUE,
    letterSpacing: TextUnit = 0.5.sp,
) {
    Text(
        text = text,
        modifier = modifier,
        style = TextStyle(
            fontSize = scalableFontSize(baseSizeSp = fontSize.sp),
            lineHeight = scalableFontSize(lineHeight.sp),
            fontFamily = fontFamily,
            fontWeight = fontWeight,
            color = color,
            letterSpacing = letterSpacing
        ),
        maxLines = if (maxLines != Int.MAX_VALUE) maxLines else Int.MAX_VALUE
    )
}