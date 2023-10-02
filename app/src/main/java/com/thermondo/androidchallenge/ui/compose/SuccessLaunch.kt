package com.thermondo.androidchallenge.ui.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thermondo.androidchallenge.model.Launch

@Composable
fun SuccessLaunch(list: List<Launch>?, navigateToDetails: (Launch) -> Unit, bookmarkClick: (Launch) -> Unit){

    if (list.isNullOrEmpty()){
        FailureComposable()
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
        ) {
            items(list) { launch ->
                LaunchListItem(
                    launch = launch,
                    navigateToDetails = navigateToDetails,
                    bookmarkClick = bookmarkClick,
                )
            }
        }
    }

/*    if (!(isLoading == true || isLastPage == true)) {
            // Implement logic to trigger loading more data here
    }*/
}