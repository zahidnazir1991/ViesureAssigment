package com.viesure.assigment.customview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.viesure.assigment.R
import com.viesure.assigment.models.TopBarActions
import com.viesure.assigment.util.NavigationActions
import com.viesure.assigment.util.scalableFontSize
import com.viesure.assigment.util.sdp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    navigationActions: NavigationActions,
    buttonVisibility: TopBarActions,
    callback: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .height(sdp(baseSizeDp = 56.dp))
            .fillMaxWidth()
            .background(Color.Black)
    ) {
        TopAppBar(
            modifier = Modifier.fillMaxSize(),
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()

                ) {

                    if (buttonVisibility.imageBack) {
                        Image(
                            painter = painterResource(R.drawable.ic_back),
                            contentDescription = "Logo",
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight()
                                .padding(end = 16.dp)
                                .clickable {
                                    navigationActions.onBackPressed()
                                },
                            colorFilter = ColorFilter.tint(Color.White)

                        )
                    }
                    if (buttonVisibility.titleText.isNotBlank()) {
                        Text(
                            text = buttonVisibility.titleText,
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight()
                            ,
                            style = TextStyle(
                                fontSize = scalableFontSize(18.sp),
                                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                fontWeight = FontWeight(400),
                                color = colorResource(id = R.color.white),
                                textAlign = TextAlign.Center,
                                letterSpacing = 0.22.sp,
                            )

                        )
                    }
                }

            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
            ),
            actions = {}

        )
    }
}



