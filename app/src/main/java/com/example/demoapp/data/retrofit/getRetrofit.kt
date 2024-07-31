package com.example.demoapp.data.retrofit

import com.example.demoapp.data.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class getRetrofit {
    private fun httpInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private fun basicOkHttpClient() = OkHttpClient.Builder().addInterceptor(httpInterceptor()).build()

    fun createBasicAuthService(): ApiService {
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).client(basicOkHttpClient())
            .baseUrl("")
            .build()
        return retrofit.create(ApiService::class.java);
    }
}