package com.thermondo.androidchallenge.ui.compose.bottomnavigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.thermondo.androidchallenge.R
import com.thermondo.androidchallenge.model.Launch
import com.thermondo.androidchallenge.ui.compose.baselineHeight
import com.thermondo.androidchallenge.utils.SpaceXHelper

@Composable
fun DetailsContent(launch: Launch, containerHeight: Dp) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))

        Name(launch, modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp))

        DetailProperty(stringResource(R.string.description), launch.details)

        DetailProperty(stringResource(R.string.flight_number), launch.flightNumber.toString())

        DetailProperty(stringResource(R.string.date),
            launch.dateUtc?.let { SpaceXHelper.formatDate(it) })

        // Add a spacer that always shows part (320.dp) of the fields list regardless of the device,
        // in order to always leave some content at the top.
        Spacer(Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }
}

@Composable
private fun Name(launch: Launch, modifier: Modifier = Modifier) {
    Text(
        text = launch.name?: "",
        modifier = modifier,
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun DetailProperty(label: String, value: String?, isLink: Boolean = false) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Divider()
        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurface) {
            Text(
                text = label,
                modifier = Modifier.baselineHeight(24.dp),
                style = MaterialTheme.typography.headlineSmall,
            )
        }
        val style = if (isLink) {
            MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primary)
        } else {
            MaterialTheme.typography.bodySmall
        }
        Text(
            text = value?: "",
            modifier = Modifier.baselineHeight(24.dp),
            style = style
        )
    }
}