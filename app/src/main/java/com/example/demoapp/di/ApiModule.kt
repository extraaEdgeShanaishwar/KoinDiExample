package com.example.demoapp.di

import com.example.demoapp.BuildConfig
import com.example.demoapp.data.ApiService
import com.example.demoapp.data.apiServiceImpl
import com.example.demoapp.utils.CONSTANTS
import com.example.demoapp.viewmodel.ApiViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log
import kotlin.math.sin

val base_url = CONSTANTS.BASE_URL
val network_timeout = CONSTANTS.NETWORK_TIMEOUT

fun provideGson():Gson = GsonBuilder().setLenient().create()

fun provideOkhttpClient() = if (BuildConfig.DEBUG){
     val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
    println("response body "+loggingInterceptor.level)

    val requestIntercepter = Interceptor {chain ->
      val url = chain.request().url.newBuilder().build()
      val request = chain.request().newBuilder().url(url).build()
      return@Interceptor chain.proceed(request)
    }

    OkHttpClient.Builder().addInterceptor(loggingInterceptor).addInterceptor(requestIntercepter).build()

}else{
   OkHttpClient.Builder().build()
}

fun provideRetrofit(baseUrl:String ,gson: Gson,okHttpClient: OkHttpClient):ApiService =
    Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create(gson)).client(okHttpClient).build().create(ApiService::class.java)

val apiModule = module {
    single{ base_url}
    single{ network_timeout}
    single { provideGson() }
    single { provideOkhttpClient() }
    single { provideRetrofit(base_url,get(),get()) }

}