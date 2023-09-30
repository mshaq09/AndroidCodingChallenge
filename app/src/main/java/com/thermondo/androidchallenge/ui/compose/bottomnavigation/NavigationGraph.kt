package com.thermondo.androidchallenge.ui.compose.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.thermondo.androidchallenge.model.Launch
import com.thermondo.androidchallenge.viewmodel.SpaceXViewModel

@Composable
fun NavigationGraph(navController: NavHostController, viewModel: SpaceXViewModel, navigateToDetails: (Launch) -> Unit, bookmarkClick: (Launch) -> Unit) {

    NavHost(navController, startDestination = Destinations.HomeScreen.route) {
        composable(Destinations.HomeScreen.route) {
            HomeScreen(viewModel, navigateToDetails, bookmarkClick)
        }
        composable(Destinations.Favourite.route) {
            FavouriteScreen(viewModel, navigateToDetails)
        }
    }
}
