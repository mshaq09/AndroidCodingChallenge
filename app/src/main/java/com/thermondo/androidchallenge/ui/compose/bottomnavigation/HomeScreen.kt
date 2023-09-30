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
import com.thermondo.androidchallenge.ui.compose.FailureComposable
import com.thermondo.androidchallenge.ui.compose.ProgressBarComposable
import com.thermondo.androidchallenge.ui.compose.SuccessLaunch
import com.thermondo.androidchallenge.ui.compose.SuccessNextComposable
import com.thermondo.androidchallenge.viewmodel.SpaceXViewModel

@Composable
fun HomeScreen(viewModel: SpaceXViewModel) {

    var tabIndex by remember { mutableStateOf(0) } // 1.
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
        renderHomeScreen(viewModel, tabIndex)
    }
}

@Composable
fun renderHomeScreen(viewModel: SpaceXViewModel, tabIndex: Int){
    viewModel.fetchLaunches(tabIndex)
    viewModel.getNextLaunch()

    when(tabIndex) {
        0 -> renderSuccessScreen(viewModel.launchesState.collectAsState().value)
        1 -> renderSuccessScreen(viewModel.upcomingState.collectAsState().value)
        2 -> renderNextScreen(viewModel.nextLaunchState.collectAsState().value)
    }


}

@Composable
fun renderSuccessScreen(collectAsState: SpaceXApiState<List<Launch>>) {

    when(collectAsState.status) {
        Status.LOADING -> ProgressBarComposable()
        Status.SUCCESS -> SuccessLaunch(collectAsState.data)
        Status.ERROR -> FailureComposable()
    }
}

@Composable
fun renderNextScreen(collectAsState: SpaceXApiState<Launch>) {

    when(collectAsState.status) {
        Status.LOADING -> ProgressBarComposable()
        Status.SUCCESS -> SuccessNextComposable(collectAsState.data)
        Status.ERROR -> FailureComposable()
    }
}






