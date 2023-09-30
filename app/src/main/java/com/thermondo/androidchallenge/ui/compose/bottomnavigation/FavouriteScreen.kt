package com.thermondo.androidchallenge.ui.compose.bottomnavigation

import androidx.compose.runtime.*
import com.thermondo.androidchallenge.service.Status
import com.thermondo.androidchallenge.ui.compose.FailureComposable
import com.thermondo.androidchallenge.ui.compose.ProgressBarComposable
import com.thermondo.androidchallenge.ui.compose.SuccessComposable
import com.thermondo.androidchallenge.ui.compose.SuccessLaunch
import com.thermondo.androidchallenge.viewmodel.SpaceXViewModel

@Composable
fun FavouriteScreen(viewModel: SpaceXViewModel) {

    val state by viewModel.bookmarkState.collectAsState()

    when(state.status) {
        Status.LOADING -> ProgressBarComposable()
        Status.SUCCESS -> SuccessLaunch(state.data)
        Status.ERROR -> FailureComposable()
    }
}