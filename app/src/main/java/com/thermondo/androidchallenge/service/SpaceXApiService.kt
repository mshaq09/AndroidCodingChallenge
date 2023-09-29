package com.thermondo.androidchallenge.service

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.thermondo.androidchallenge.model.Launch
import retrofit2.http.GET

interface SpaceXApiService {

    @GET("launches")
    suspend fun getLaunches(): SnapshotStateList<Launch>
}