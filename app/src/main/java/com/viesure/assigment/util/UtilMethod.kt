package com.viesure.assigment.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.net.URLEncoder
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

object AppState {
    val showLoader = mutableStateOf(false)
    val showToaststate = mutableStateOf(false)
    val showToastmessage = mutableStateOf("")
    val showToasttype = mutableStateOf(ToastType.FAIL)
}


@Composable
fun Float.toScalableSp(): TextUnit {
    val density = LocalDensity.current.density
    return (this * density).sp
}


@Composable
fun scalableFontSize(baseSize: Float): Float {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val standardScreenWidth = 360.dp

    val scale = screenWidth / standardScreenWidth
    return baseSize * scale
}


@Composable
fun scalableFontSize(baseSizeSp: TextUnit): TextUnit {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val standardScreenWidth = 360.dp  // Base screen width, adjust as needed

    val scale = screenWidth / standardScreenWidth
    return (baseSizeSp.value * scale).sp
}

@Composable
fun sdp(baseSizeDp: Float): Dp {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val standardScreenWidth = 360.dp  // Standard width you are designing for, like 360dp

    val scale = screenWidth / standardScreenWidth
    return (baseSizeDp * scale).dp
}

@Composable
fun sdp(baseSizeDp: Dp): Dp {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val standardScreenWidth = 360.dp  // Standard width you are designing for, like 360dp

    val scale = screenWidth / standardScreenWidth
    return baseSizeDp * scale
}

fun encodeUri(uri: String?): String? {
    if (uri == null) return null
    return try {
        URLEncoder.encode(uri, "UTF-8")
    } catch (e: UnsupportedEncodingException) {
        e.printStackTrace()
        null
    }
}

fun decodeUri(encodedUri: String?): String? {
    if (encodedUri == null) return null
    return try {
        URLDecoder.decode(encodedUri, "UTF-8")
    } catch (e: UnsupportedEncodingException) {
        e.printStackTrace()
        null
    }
}

fun objectToJSONString(obj: Any?): String? {
    val gson = Gson()
    return gson.toJson(obj)
}

inline fun <reified T> fromJson(jsonString: String): T {
    val gson = Gson()
    return gson.fromJson(jsonString, T::class.java)
}

fun formatDate(inputDate: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("M/d/yyyy", Locale.ENGLISH)
    val outputFormatter = DateTimeFormatter.ofPattern("E, MMM d, yy", Locale.ENGLISH)
    return try {
        val date = LocalDate.parse(inputDate, inputFormatter)
        date.format(outputFormatter)
    } catch (e: DateTimeParseException) {
        inputDate
    }
}