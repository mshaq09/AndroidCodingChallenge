package com.thermondo.androidchallenge.repository

import com.thermondo.androidchallenge.service.SpaceXApiService

class SpaceXRepository (private val spaceXApiService: SpaceXApiService) {
    suspend fun getLaunches() = spaceXApiService.getLaunches()
}