package com.thermondo.androidchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.toArgb
import com.thermondo.androidchallenge.ui.compose.DetailsScreen
import com.thermondo.androidchallenge.ui.theme.AndroidCodingChallengeTheme
import com.thermondo.androidchallenge.viewmodel.SpaceXViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LaunchDetailsActivity : ComponentActivity() {

    val spaceXViewModel: SpaceXViewModel by viewModel()

    private val launchId: String by lazy {
        intent?.getStringExtra("id").toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        spaceXViewModel.getLaunch(id = launchId)
        setContent {
            AndroidCodingChallengeTheme {
                window.statusBarColor = MaterialTheme.colorScheme.primary.toArgb()
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.background) {
                    val data = spaceXViewModel.launchState.collectAsState()
                    DetailsScreen(data.value)
                }
            }
        }
    }
}