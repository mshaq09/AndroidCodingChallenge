package com.thermondo.androidchallenge.repository

import com.thermondo.androidchallenge.database.LaunchesDao
import com.thermondo.androidchallenge.model.Launch
import com.thermondo.androidchallenge.service.SpaceXApiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class LaunchRepository(private val launchesDao: LaunchesDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    suspend fun getBookMarkedLaunches(): Flow<SpaceXApiState<List<Launch>>> {
        return flow {

            // get the launches Data from the api
            val launches = launchesDao.getAllBookMarked()

            // Emit this data wrapped in
            // the helper class [SpaceXApiState]
            emit(SpaceXApiState.success(launches))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun addBookMark(launch: Launch) {
        coroutineScope.launch(Dispatchers.IO) {
            launchesDao.addBookMark(launch)
        }
    }

    suspend fun deleteBookMark(launch: Launch) {
        coroutineScope.launch(Dispatchers.IO) {
            launchesDao.deleteBookMark(launch)
        }
    }


}