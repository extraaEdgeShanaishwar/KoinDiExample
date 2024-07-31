package com.example.demoapp.repository

import com.example.demoapp.data.ApiService
import com.example.demoapp.utils.DataStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ApiRepository (private val ApiService: ApiService){

    suspend fun getCatFact()  = flow {
        emit(DataStatus.loading())
        val result = ApiService.getFact()
        when (result.code()){
            200 -> emit(DataStatus.success(result.body()))
            else -> emit(DataStatus.error(result.message()))
        }
    }
        .catch { emit(DataStatus.error(it.message.toString())) }
        .flowOn(Dispatchers.IO)

}