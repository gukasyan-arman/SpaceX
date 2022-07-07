package com.example.spacex.data.retrofit.api

import com.example.spacex.models.ResponseModel
import com.example.spacex.utils.END_POINT
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(END_POINT)
    suspend fun getLaunches(): Response<ResponseModel>
}