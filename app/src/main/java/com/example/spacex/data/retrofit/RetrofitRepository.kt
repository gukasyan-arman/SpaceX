package com.example.spacex.data.retrofit

import com.example.spacex.data.retrofit.api.RetrofitInstance
import com.example.spacex.models.ResponseModel
import retrofit2.Response

class RetrofitRepository {
    suspend fun getLaunches(): Response<ResponseModel> {
        return RetrofitInstance.api.getLaunches()
    }
}