package com.example.demoapp.data

import com.example.demoapp.domain.use_case.AuthRepository

class AuthRepoImpl(private val apiServiceImpl: ApiService , private val apiSevivceSecondImpl: ApiService) :AuthRepository {
    override fun login(name: String, password: String): Boolean {
   return apiServiceImpl.login(name,password)
    }

    override fun getUser(): String {
       return apiSevivceSecondImpl.getUser()
    }

}