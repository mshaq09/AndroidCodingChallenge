package com.thermondo.androidchallenge.repository

import com.thermondo.androidchallenge.model.Launch
import com.thermondo.androidchallenge.service.SpaceXApiService
import com.thermondo.androidchallenge.service.SpaceXApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SpaceXRepository (private val spaceXApiService: SpaceXApiService) {

    suspend fun getLaunches(): Flow<SpaceXApiState<List<Launch>>> {
        return flow {
            val launches = spaceXApiService.getLaunches()
            emit(SpaceXApiState.success(launches))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getUpcomingLaunches(): Flow<SpaceXApiState<List<Launch>>> {
        return flow {
            val launches = spaceXApiService.getUpcomingLaunches()
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

    suspend fun getLaunch(id: String): Flow<SpaceXApiState<Launch>> {
        return flow {
            val launches = spaceXApiService.getLaunch(id)
            emit(SpaceXApiState.success(launches))
        }.flowOn(Dispatchers.IO)
    }
}