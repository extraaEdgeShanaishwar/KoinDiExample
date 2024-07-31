package com.example.demoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapp.model.CatFactResp
import com.example.demoapp.repository.ApiRepository
import com.example.demoapp.utils.DataStatus
import kotlinx.coroutines.launch

class ApiViewModel (private val apiRepository: ApiRepository):ViewModel(){

   val _factData = MutableLiveData<DataStatus<CatFactResp>>()
    val  factData: LiveData<DataStatus<CatFactResp>> = _factData


    fun getCatFact() {
        viewModelScope.launch {
            apiRepository.getCatFact().collect {
                _factData.value = it
            }
        }
    }
}