package com.viesure.assigment.customview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.viesure.assigment.R
import com.viesure.assigment.models.BookDetailsResponseItem
import com.viesure.assigment.uiscreens.BookDetailsScreen
import com.viesure.assigment.uiscreens.BookListingScreen
import com.viesure.assigment.util.NavigationActions
import com.viesure.assigment.util.Screen
import com.viesure.assigment.util.decodeUri
import com.viesure.assigment.util.fromJson
import com.viesure.assigment.viewmodels.NavigationViewModel


@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: NavigationViewModel
) {
    val navigationActions = remember { NavigationActions(navController) }
    NavHost(
        navController = navController,
        startDestination = Screen.BOOKLISTING.route

    ) {
        composable(Screen.BOOKLISTING.route) {
            val titleText = stringResource(id = R.string.string_main)
            LaunchedEffect(key1 = Unit) {
                viewModel.TopBarSettings(
                    isShowBackButton = false,
                    titleText = titleText,
                )
            }
            BookListingScreen(navigationActions)
        }

        composable(
            Screen.BOOKDETAILS.route + "/{json}",
            arguments = listOf(navArgument("json") { type = NavType.StringType })
        ) {
            val titleText = stringResource(id = R.string.string_details)
            val json = it.arguments?.getString("json") ?: ""
            val item: BookDetailsResponseItem = fromJson(decodeUri(json) ?: "")
            LaunchedEffect(key1 = Unit) {
                viewModel.TopBarSettings(
                    isShowBackButton = true,
                    titleText = titleText,
                )
            }
            BookDetailsScreen(item)
        }


    }

}




