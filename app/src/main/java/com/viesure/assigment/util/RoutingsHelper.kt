package com.viesure.assigment.util

import androidx.navigation.NavHostController
import com.viesure.assigment.models.BookDetailsResponseItem
import javax.inject.Inject

sealed class Screen(val route: String) {
    object BOOKLISTING : Screen("Booklisting")
    object BOOKDETAILS : Screen("Bookdetails")

}

class NavigationActions @Inject constructor(private val navController: NavHostController) {

    fun navigateToDestination(destination: String) {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        val previousRoute = navController.previousBackStackEntry?.destination?.route
        if (currentRoute != destination && previousRoute != destination) {
            navController.navigate(destination) {
                anim {
                    enter = 0
                    exit = 0
                    popEnter = 0
                    popExit = 0
                }
                launchSingleTop = true
                restoreState = true
                popUpToRoute?.let {
                    popUpTo(it) {
                        inclusive = true
                    }
                }
            }
        } else if (previousRoute == destination) {
            // If the previous destination in the back stack is the one we want, navigate back to it
            navController.popBackStack()
        }
        // If the current destination is already the one we want, do nothing
    }
    fun navigateToDestination1(destination: String) {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        val previousRoute = navController.previousBackStackEntry?.destination?.route

        if (currentRoute != destination) {
            navController.navigate(destination) {

                launchSingleTop = true
                restoreState = true
                popUpTo(navController.graph.startDestinationId) {
                    saveState = true
                }
            }
        } else if (previousRoute == destination) {
            // If the previous destination in the back stack is the one we want, navigate back to it
            navController.popBackStack()
        }
        // If the current destination is already the one we want, do nothing
    }


    fun navigateToMain() =
        navigateToDestination1(Screen.BOOKLISTING.route)

    fun navigateToDetailsScreen(selectedItem: BookDetailsResponseItem) {
        var json = encodeUri(objectToJSONString(selectedItem))
        navigateToDestination1(
            Screen.BOOKDETAILS.route + "/$json"
        )
    }

    fun onBackPressed() {
        navController.popBackStack()
    }

}

