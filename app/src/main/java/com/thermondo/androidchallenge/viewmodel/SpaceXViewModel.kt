package com.thermondo.androidchallenge.viewmodel

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.*
import com.thermondo.androidchallenge.model.Launch
import com.thermondo.androidchallenge.repository.SpaceXRepository
import kotlinx.coroutines.launch

class SpaceXViewModel (
    private val spaceXRepository: SpaceXRepository
) : ViewModel() {

    private val _launches = MutableLiveData<SnapshotStateList<Launch>>()
    val launches: LiveData<SnapshotStateList<Launch>> = _launches

    init {
        fetchLaunches()
    }

    private fun fetchLaunches() {
        viewModelScope.launch {
            _launches.postValue(spaceXRepository.getLaunches())
        }

    }
}