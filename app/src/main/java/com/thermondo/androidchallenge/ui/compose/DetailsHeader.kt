package com.thermondo.androidchallenge.ui.compose

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import com.thermondo.androidchallenge.model.Launch

@Composable
fun DetailsHeader(
    scrollState: ScrollState,
    launch: Launch,
    containerHeight: Dp
) {

    val offset = (scrollState.value / 2)
    val offsetDp = with(LocalDensity.current) { offset.toDp() }

    AsyncImage(
        model = launch.links?.patch?.large,
        contentDescription = null,
        modifier = Modifier
            .heightIn(max = containerHeight / 2)
            .fillMaxWidth()
            .padding(top = offsetDp),
        contentScale = ContentScale.Crop,
    )
}
