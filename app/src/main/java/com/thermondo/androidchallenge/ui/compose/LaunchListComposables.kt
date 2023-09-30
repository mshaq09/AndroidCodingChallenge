package com.thermondo.androidchallenge.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.thermondo.androidchallenge.model.Launch
import com.thermondo.androidchallenge.ui.compose.bottomnavigation.BottomBar
import com.thermondo.androidchallenge.ui.compose.bottomnavigation.NavigationGraph
import com.thermondo.androidchallenge.viewmodel.SpaceXViewModel

@Composable
fun LaunchListComposables (
    viewModel: SpaceXViewModel,
    navigateToDetails: (Launch) -> Unit,
    bookmarkClick: (Launch) -> Unit
) {
    val navController: NavHostController = rememberNavController()
    val bottomBarHeight = 70.dp

    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController,
                modifier = Modifier
                    .height(bottomBarHeight)
            )
        }) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            NavigationGraph(navController = navController, viewModel = viewModel, navigateToDetails, bookmarkClick)
        }
    }
}
