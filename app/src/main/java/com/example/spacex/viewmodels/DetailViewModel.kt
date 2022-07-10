package com.example.spacex.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacex.data.retrofit.RetrofitRepository
import com.example.spacex.models.ResponseCrew
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailViewModel: ViewModel() {
    val repository = RetrofitRepository()
    val crew: MutableLiveData<Response<ResponseCrew>> = MutableLiveData()

    fun getCrew() {
        viewModelScope.launch {
            crew.value = repository.getCrew()
        }
    }
}