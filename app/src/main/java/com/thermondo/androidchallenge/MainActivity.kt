package com.thermondo.androidchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.toArgb
import com.thermondo.androidchallenge.ui.compose.LaunchListComposables
import com.thermondo.androidchallenge.ui.theme.AndroidCodingChallengeTheme
import com.thermondo.androidchallenge.viewmodel.SpaceXViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    val spaceXViewModel: SpaceXViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidCodingChallengeTheme {
                window.statusBarColor = MaterialTheme.colorScheme.primary.toArgb()
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.background) {
                   LaunchListComposables(viewModel = spaceXViewModel)
                }
            }
        }
    }
}