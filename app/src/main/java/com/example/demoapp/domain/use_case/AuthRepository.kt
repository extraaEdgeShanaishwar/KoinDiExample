package com.example.demoapp.domain.use_case

interface AuthRepository {
    fun login(name:String , password:String): Boolean

    fun getUser():String
}