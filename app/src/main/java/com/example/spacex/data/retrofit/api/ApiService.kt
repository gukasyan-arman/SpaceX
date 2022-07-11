package com.example.spacex.data.retrofit.api

import com.example.spacex.models.ResponseCrew
import com.example.spacex.models.ResponseModel
import com.example.spacex.utils.CREW_END_POINT
import com.example.spacex.utils.LAUNCH_END_POINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(LAUNCH_END_POINT)
    suspend fun getLaunches(): Response<ResponseModel>

    @GET(CREW_END_POINT)
    suspend fun getCrew(): Response<ResponseCrew>
}