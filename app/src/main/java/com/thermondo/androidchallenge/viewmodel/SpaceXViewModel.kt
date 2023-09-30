package com.thermondo.androidchallenge.viewmodel

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.*
import com.thermondo.androidchallenge.model.Launch
import com.thermondo.androidchallenge.repository.LaunchRepository
import com.thermondo.androidchallenge.repository.SpaceXRepository
import com.thermondo.androidchallenge.service.SpaceXApiState
import com.thermondo.androidchallenge.service.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class SpaceXViewModel (
    private val spaceXRepository: SpaceXRepository,
    private val launchRepository: LaunchRepository
) : ViewModel() {

    private val launch = listOf<Launch>()
    var  upcomingState: MutableStateFlow<SpaceXApiState<List<Launch>>>
    var launchState: MutableStateFlow<SpaceXApiState<Launch>>

    val launchesState = MutableStateFlow(
        SpaceXApiState(
            Status.LOADING,
            launch, ""
        )
    ).also{ upcomingState = it}

    val nextLaunchState = MutableStateFlow(
        SpaceXApiState(
            Status.LOADING,
            Launch(id=""), ""
        )
    ).also { launchState = it }

    val bookmarkState = MutableStateFlow(
        SpaceXApiState(
            Status.LOADING,
            launch, ""
        )
    )

    fun fetchLaunches() {
        // Since Network Calls takes time,Set the
        // initial value to loading state
         launchesState.value = SpaceXApiState.loading()

        // ApiCalls takes some time, So it has to be
        // run and background thread. Using viewModelScope
        // to call the api
        viewModelScope.launch {

            // Collecting the data emitted
            // by the function in repository
            spaceXRepository.getLaunches()
                // If any errors occurs like 404 not found
                // or invalid query, set the state to error
                // State to show some info
                // on screen
                .catch {
                    launchesState.value = SpaceXApiState.error(it.message.toString())
                }
                // If Api call is succeeded, set the State to Success
                // and set the response data to data received from api
                .collect {
                    launchesState.value = SpaceXApiState.success(it.data)
                }
        }
    }

    fun fetchUpcomingLaunches() {
        upcomingState.value = SpaceXApiState.loading()
        viewModelScope.launch {
            spaceXRepository.getUpcomingLaunches()
                .catch {
                    upcomingState.value = SpaceXApiState.error(it.message.toString())
                }
                .collect {
                    upcomingState.value = SpaceXApiState.success(it.data)
                }
        }
    }

    fun getNextLaunch() {
        nextLaunchState.value = SpaceXApiState.loading()
        viewModelScope.launch {
            spaceXRepository.getNextLaunch()
                .catch {
                    nextLaunchState.value =
                        SpaceXApiState.error(it.message.toString())
                }
                .collect {
                    nextLaunchState.value = SpaceXApiState.success(it.data)
                }
        }
    }

    fun getLaunch(id: String) {
        launchState.value = SpaceXApiState.loading()
        viewModelScope.launch {
            spaceXRepository.getLaunch(id)
                .catch {
                    launchState.value =
                        SpaceXApiState.error(it.message.toString())
                }
                .collect {
                    launchState.value = SpaceXApiState.success(it.data)
                }
        }
    }

    fun getFavoriteLaunches() {
        bookmarkState.value = SpaceXApiState.loading()
        viewModelScope.launch {
            launchRepository.getBookMarkedLaunches()
                .catch {
                    bookmarkState.value =
                        SpaceXApiState.error(it.message.toString())
                }
                .collect {
                    bookmarkState.value = SpaceXApiState.success(it.data)
                }
        }
    }

    fun addFavoriteLaunch(launch: Launch) {
        viewModelScope.launch {
            launchRepository.addBookMark(launch)
        }
    }

    fun deleteBookMark(launch: Launch) {
        viewModelScope.launch {
            launchRepository.deleteBookMark(launch)
        }
    }
}