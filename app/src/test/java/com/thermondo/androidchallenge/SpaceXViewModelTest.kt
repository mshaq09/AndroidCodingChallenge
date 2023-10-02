package com.thermondo.androidchallenge

import com.thermondo.androidchallenge.model.Launch
import com.thermondo.androidchallenge.repository.LaunchRepository
import com.thermondo.androidchallenge.repository.SpaceXRepository
import com.thermondo.androidchallenge.service.SpaceXApiState
import com.thermondo.androidchallenge.service.Status
import com.thermondo.androidchallenge.viewmodel.SpaceXViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class SpaceXViewModelTest {

    private lateinit var viewModel: SpaceXViewModel

    @Mock
    private lateinit var spaceXRepository: SpaceXRepository

    @Mock
    private lateinit var launchRepository: LaunchRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = SpaceXViewModel(spaceXRepository, launchRepository)
    }

    @Test
    fun `fetchLaunches should set loading and success states correctly`() = runBlocking {
        // Prepare test data
        val fakeLaunches = listOf<Launch>(/* Add your fake launch data here */)

        // Mock the behavior of spaceXRepository.getLaunches()
        Mockito.`when`(spaceXRepository.getLaunches()).thenReturn(flowOf(SpaceXApiState.success(fakeLaunches)))

        // Call the function to be tested
        viewModel.fetchLaunches()

        // Assert that the loading state is set first
        assert(viewModel.launchesState.value.status == Status.LOADING)

        // Assert that the success state is set with the correct data
        assert(viewModel.launchesState.value.status == Status.SUCCESS)
        assert(viewModel.launchesState.value.data == fakeLaunches)
    }

    // Similar tests can be written for fetchUpcomingLaunches() and getNextLaunch()
}