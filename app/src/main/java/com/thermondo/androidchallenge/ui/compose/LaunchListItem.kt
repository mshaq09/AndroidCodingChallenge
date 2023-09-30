package com.thermondo.androidchallenge.ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.thermondo.androidchallenge.model.Launch

@Composable
fun LaunchListItem(launch: Launch) {

    val icon = if (launch.isBookmarked) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder

    Card(
        modifier = Modifier
            // The space between each card and the other
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = launch.links?.patch?.small,
                contentDescription = null,
                modifier = Modifier.size(130.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Fit,
            )

            Column(Modifier.padding(8.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = launch.name,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Icon(imageVector = icon , contentDescription = "")
                }
                Text(
                    text = launch.details?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 4.dp),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis, fontSize = 20.sp
                )

            }
        }
    }
}