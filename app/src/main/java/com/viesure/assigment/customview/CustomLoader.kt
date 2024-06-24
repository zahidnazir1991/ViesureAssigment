package com.viesure.assigment.customview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.viesure.assigment.R
import com.viesure.assigment.util.sdp

@Composable
fun CustomLoader(showDialogState: MutableState<Boolean>) {
    if (showDialogState.value) {
        Dialog(
            onDismissRequest = { showDialogState.value = false },
            DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(sdp(baseSizeDp = 65.dp))
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(sdp(48.dp))
                        .height(sdp(48.dp)),
                    color = colorResource(id = R.color.loaderdefault),
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        }
    }
}