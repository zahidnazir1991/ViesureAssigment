package com.viesure.assigment.uiscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.viesure.assigment.R
import com.viesure.assigment.customview.CustomErrorScreen
import com.viesure.assigment.customview.CustomLoader
import com.viesure.assigment.recyclerview.BooksRecyclerView
import com.viesure.assigment.util.AppState
import com.viesure.assigment.util.NavigationActions
import com.viesure.assigment.util.sdp
import com.viesure.assigment.viewmodels.BookListingViewModel

@Composable
fun BookListingScreen(navigationActions: NavigationActions) {
    val viewModel: BookListingViewModel = hiltViewModel()
    val booksLoaded = rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = booksLoaded.value) {
        if (!booksLoaded.value) {
            viewModel.getBooks()
            booksLoaded.value = true
        }
    }
    val data = viewModel.data.collectAsState().value

    if (viewModel.isShowerror.value) {
        Box(
            modifier = Modifier
                .background(color = Color(0xFFF1F1F1))
                .fillMaxSize()
                .padding(
                    top = sdp(baseSizeDp = 8.dp),
                    bottom = sdp(baseSizeDp = 20.dp),
                    start = sdp(baseSizeDp = 8.dp),
                    end = sdp(baseSizeDp = 8.dp)
                ), contentAlignment = Alignment.Companion.Center
        ) {
            CustomErrorScreen(
                viewModel.errorMessage.value
            )
        }
    } else {
        Box(
            modifier = Modifier
                .background(color = Color(0xFFF1F1F1))
                .fillMaxSize()
                .padding(
                    top = sdp(baseSizeDp = 8.dp),
                    bottom = sdp(baseSizeDp = 20.dp),
                    start = sdp(baseSizeDp = 8.dp),
                    end = sdp(baseSizeDp = 8.dp)
                ), contentAlignment = Alignment.Companion.TopCenter
        ) {
            BooksRecyclerView(data) { book ->
                navigationActions.navigateToDetailsScreen(book)

            }
        }
    }
    CustomLoader(showDialogState = AppState.showLoader)
}