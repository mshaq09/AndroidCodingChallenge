package com.thermondo.androidchallenge.ui.compose.bottomnavigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thermondo.androidchallenge.model.Launch
import com.thermondo.androidchallenge.service.SpaceXApiState
import com.thermondo.androidchallenge.service.Status
import com.thermondo.androidchallenge.ui.compose.*
import com.thermondo.androidchallenge.viewmodel.SpaceXViewModel

@Composable
fun HomeScreen(
    viewModel: SpaceXViewModel,
    navigateToDetails: (Launch) -> Unit,
    bookmarkClick: (Launch) -> Unit
) {

    var tabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("All", "Upcoming", "Next Launch")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp),
    ) {
        TabRow(selectedTabIndex = tabIndex) {
            tabTitles.forEachIndexed { index, title ->
                Tab(selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    text = { Text(text = title) })
            }
        }
        renderHomeScreen(viewModel, tabIndex, navigateToDetails, bookmarkClick)
    }
}

@Composable
fun renderHomeScreen(
    viewModel: SpaceXViewModel,
    tabIndex: Int,
    navigateToDetails: (Launch) -> Unit,
    bookmarkClick: (Launch) -> Unit
) {

    // API call
    LaunchedEffect(key1 = 1) {
        viewModel.fetchLaunches()
        viewModel.fetchUpcomingLaunches()
        viewModel.getNextLaunch()
    }

    when (tabIndex) {
        0 -> renderSuccessScreen(viewModel.launchesState.collectAsState().value, navigateToDetails, bookmarkClick)
        1 -> renderSuccessScreen(viewModel.upcomingState.collectAsState().value, navigateToDetails, bookmarkClick)
        2 -> renderNextScreen(viewModel.nextLaunchState.collectAsState().value)
    }
}

@Composable
fun renderSuccessScreen(
    collectAsState: SpaceXApiState<List<Launch>>,
    navigateToDetails: (Launch) -> Unit,
    bookmarkClick: (Launch) -> Unit
) {

    when (collectAsState.status) {
        Status.LOADING -> ProgressBarComposable()
        Status.SUCCESS -> SuccessLaunch(collectAsState.data, navigateToDetails, bookmarkClick)
        Status.ERROR -> FailureComposable()
    }
}

@Composable
fun renderNextScreen(collectAsState: SpaceXApiState<Launch>) {

    when (collectAsState.status) {
        Status.LOADING -> ProgressBarComposable()
        Status.SUCCESS -> DetailsScreen(collectAsState)
        Status.ERROR -> FailureComposable()
    }
}






