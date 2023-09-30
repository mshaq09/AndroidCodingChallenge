package com.thermondo.androidchallenge.ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.thermondo.androidchallenge.model.Launch

@Composable
fun SuccessLaunch(list: List<Launch>?){

    if (list.isNullOrEmpty()){

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("No Records",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
        }

    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
        ) {
            items(list) { launch ->
                LaunchListItem(
                    launch = launch
                )
            }
        }
    }
}