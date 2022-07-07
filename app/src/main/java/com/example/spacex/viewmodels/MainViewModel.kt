package com.example.spacex.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacex.data.retrofit.RetrofitRepository
import com.example.spacex.models.ResponseModel
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel: ViewModel() {
    val repository = RetrofitRepository()
    val launches: MutableLiveData<Response<ResponseModel>> = MutableLiveData()

    fun getLaunches() {
        viewModelScope.launch {
            launches.value = repository.getLaunches()
        }
    }

}