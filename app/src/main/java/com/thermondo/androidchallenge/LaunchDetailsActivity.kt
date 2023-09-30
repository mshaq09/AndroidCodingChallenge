package com.thermondo.androidchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.toArgb
import com.thermondo.androidchallenge.ui.theme.AndroidCodingChallengeTheme

class LaunchDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidCodingChallengeTheme {
                window.statusBarColor = MaterialTheme.colorScheme.primary.toArgb()
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.background) {
                    Text(text = "Hello details!")
                }
            }
        }
    }
}