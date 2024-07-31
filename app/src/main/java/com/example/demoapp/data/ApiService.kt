package com.example.demoapp.data

import com.example.demoapp.model.CatFactResp
import com.example.demoapp.server.FirstServer
import com.example.demoapp.server.SecondServer
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    fun login(name : String , password:String):Boolean

    fun getUser():String

    @GET("/fact")
    suspend fun getFact():Response<CatFactResp>

}

class apiServiceImpl(private val firstServer: FirstServer):ApiService{
    override fun login(name: String, password: String): Boolean {
         return firstServer.performLogin()
    }

    override fun getUser(): String {
        return "Wrong server used for used data"
    }

    override suspend fun getFact(): Response<CatFactResp> {
     TODO("Not yet implemented")
    }

}

class apiSevivceSecondImpl(private val secondServer: SecondServer):ApiService{
    override fun login(name: String, password: String): Boolean {
         return false
    }

    override fun getUser(): String {
        return secondServer.getUser()
    }

    override suspend fun getFact(): Response<CatFactResp> {
        TODO("Not yet implemented")
    }


}