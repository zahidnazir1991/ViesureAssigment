package com.viesure.assigment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.viesure.assigment.customview.AppNavigation
import com.viesure.assigment.customview.CustomTopAppBar
import com.viesure.assigment.ui.theme.AssignmentTheme
import com.viesure.assigment.util.NavigationActions
import com.viesure.assigment.viewmodels.NavigationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssignmentTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    MyApp()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview
    @Composable
    fun MyApp() {
        val navController = rememberNavController()
        val viewModel: NavigationViewModel = hiltViewModel()
        val buttonVisibility = viewModel.buttonVisibility_.collectAsState().value
        /// val navigationActions = remember { NavigationActions(navController) }
        Scaffold(
            topBar = {
                CustomTopAppBar(
                    NavigationActions(navController), buttonVisibility, {}
                )
            },
            content = { innerPadding ->
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxHeight()
                        .background(Color.Black)
                ) {
                    AppNavigation(navController,viewModel)

                }

            }
        )

    }
}

