package com.thermondo.androidchallenge.ui.compose

import android.util.Log
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.thermondo.androidchallenge.model.Launch
import com.thermondo.androidchallenge.service.SpaceXApiState
import com.thermondo.androidchallenge.service.Status
import com.thermondo.androidchallenge.ui.compose.bottomnavigation.DetailsContent

@Composable
fun DetailsScreen (collectAsState: SpaceXApiState<Launch>) {

    when(collectAsState.status) {
        Status.LOADING -> ProgressBarComposable()
        Status.SUCCESS -> collectAsState.data?.let { DetailsSuccess(it) }
        Status.ERROR -> FailureComposable()
    }

}

@Composable
fun DetailsSuccess(launch: Launch){
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                ) {
                    DetailsHeader(
                        scrollState,
                        launch,
                        this@BoxWithConstraints.maxHeight
                    )
                    DetailsContent(
                        launch,
                        this@BoxWithConstraints.maxHeight
                    )
                }
            }
        }
    }
}
