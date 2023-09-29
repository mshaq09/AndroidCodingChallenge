package com.thermondo.androidchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import com.thermondo.androidchallenge.ui.compose.LaunchListComposables
import com.thermondo.androidchallenge.ui.theme.AndroidCodingChallengeTheme
import com.thermondo.androidchallenge.viewmodel.SpaceXViewModel

class MainActivity : ComponentActivity() {

    val spaceXViewModel: SpaceXViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidCodingChallengeTheme {
                window.statusBarColor = MaterialTheme.colorScheme.primary.toArgb()
                // A surface container using the 'background' color from the theme
                val items by spaceXViewModel.launches.observeAsState()
                Surface(color = MaterialTheme.colorScheme.background) {
                    items?.let { LaunchListComposables(launches = it) }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidCodingChallengeTheme {
        Greeting("thermondo Android coding challenge")
    }
}