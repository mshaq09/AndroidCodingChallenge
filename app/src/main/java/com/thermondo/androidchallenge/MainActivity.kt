package com.thermondo.androidchallenge

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.toArgb
import com.thermondo.androidchallenge.model.Launch
import com.thermondo.androidchallenge.ui.compose.LaunchListComposables
import com.thermondo.androidchallenge.ui.theme.AndroidCodingChallengeTheme
import com.thermondo.androidchallenge.viewmodel.SpaceXViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val spaceXViewModel: SpaceXViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidCodingChallengeTheme {
                window.statusBarColor = MaterialTheme.colorScheme.primary.toArgb()
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.background) {
                    LaunchListComposables(
                        viewModel = spaceXViewModel,
                        navigateToDetails = { onClickNavigate(it) },
                        bookmarkClick = { onClickBookMark(it) })
                }
            }
        }
    }

    fun onClickBookMark(launch: Launch) {
        spaceXViewModel.addFavoriteLaunch(launch)
    }

    fun onClickNavigate(launch: Launch){
        val intent = Intent(this, LaunchDetailsActivity::class.java)
        intent.putExtra("id",launch.id )
        startActivity(intent)
    }
}