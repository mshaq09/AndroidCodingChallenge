package com.thermondo.androidchallenge.ui.compose.bottomnavigation

import androidx.compose.runtime.*
import com.thermondo.androidchallenge.model.Launch
import com.thermondo.androidchallenge.service.Status
import com.thermondo.androidchallenge.ui.compose.FailureComposable
import com.thermondo.androidchallenge.ui.compose.ProgressBarComposable
import com.thermondo.androidchallenge.ui.compose.SuccessLaunch
import com.thermondo.androidchallenge.viewmodel.SpaceXViewModel

@Composable
fun FavouriteScreen(viewModel: SpaceXViewModel, navigateToDetails: (Launch) -> Unit, ) {

    var refreshCount by remember { mutableStateOf(1) }

    LaunchedEffect(key1 = refreshCount) {
        viewModel.getFavoriteLaunches()
    }

    val state by viewModel.bookmarkState.collectAsState()

    when(state.status) {
        Status.LOADING -> ProgressBarComposable()
        Status.SUCCESS -> SuccessLaunch(state.data, navigateToDetails, bookmarkClick = {
                bookmarkClick(viewModel, it)
                refreshCount++
            }
        )
        Status.ERROR -> FailureComposable()
    }
}

fun bookmarkClick(viewModel: SpaceXViewModel, launch: Launch) {
    viewModel.deleteBookMark(launch)
}