package com.thermondo.androidchallenge.repository

import com.thermondo.androidchallenge.model.Launch
import com.thermondo.androidchallenge.service.SpaceXApiService
import com.thermondo.androidchallenge.service.SpaceXApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SpaceXRepository (private val spaceXApiService: SpaceXApiService) {

    suspend fun getLaunches(tabIndex: Int): Flow<SpaceXApiState<List<Launch>>> {
        return flow {

            // get the launches Data from the api

            val launches = when(tabIndex){
                0 -> spaceXApiService.getLaunches()
                1 -> spaceXApiService.getUpcomingLaunches()
                else -> listOf<Launch>()
            }

            // Emit this data wrapped in
            // the helper class [SpaceXApiState]
            emit(SpaceXApiState.success(launches))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getNextLaunch(): Flow<SpaceXApiState<Launch>> {
        return flow {

            // get the launches Data from the api
            val launches = spaceXApiService.getNextLaunch()

            // Emit this data wrapped in
            // the helper class [SpaceXApiState]
            emit(SpaceXApiState.success(launches))
        }.flowOn(Dispatchers.IO)
    }
}