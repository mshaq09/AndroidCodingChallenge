package com.thermondo.androidchallenge.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.thermondo.androidchallenge.model.Launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchListComposables (
    launches: SnapshotStateList<Launch>
) {
    val list = remember { launches }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(com.thermondo.androidchallenge.R.string.app_name)) }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 38.dp),
            contentPadding = PaddingValues(16.dp),
        ) {
            items(list){ launch ->
                LaunchListItem(
                    launch = launch
                )
            }
        }
    }
}
