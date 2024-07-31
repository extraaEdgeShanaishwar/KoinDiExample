package com.example.demoapp.di

import com.example.demoapp.data.ApiService
import com.example.demoapp.data.AuthRepoImpl
import com.example.demoapp.data.apiServiceImpl
import com.example.demoapp.data.apiSevivceSecondImpl
import com.example.demoapp.data.retrofit.getRetrofit
import com.example.demoapp.domain.use_case.AuthRepository
import com.example.demoapp.domain.use_case.GetUserDataUseCase
import com.example.demoapp.domain.use_case.LoginUseCase
import com.example.demoapp.repository.ApiRepository
import com.example.demoapp.server.FirstServer
import com.example.demoapp.server.SecondServer
import com.example.demoapp.ui.AuthViewModel
import com.example.demoapp.ui.MainActivity
import com.example.demoapp.ui.ScopeTestingClass
import com.example.demoapp.viewmodel.ApiViewModel
import io.reactivex.internal.schedulers.RxThreadFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module
import org.koin.dsl.scoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val LOGIN_SERVER = "login"
 const val USER_SERVER = "user"

val authModule = module {
    // single keyword is used to create a single instance of the class
//    single {  }

    // factory keyword is used to create a new instance of the class each time it is requested
    // here we are creating a new instance of the class each time it is requested
    factory { FirstServer() }
    factory { SecondServer() }

    factory<ApiService> (qualifier = named(LOGIN_SERVER)) { apiServiceImpl(firstServer = get()) }
    factory<ApiService> (qualifier = named(USER_SERVER)) { apiSevivceSecondImpl(secondServer = get()) }

    //creating object of auth repository
    factory<AuthRepository> { AuthRepoImpl(apiServiceImpl = get(named(LOGIN_SERVER)) , apiSevivceSecondImpl = get (named( USER_SERVER))) }

    // here we are creating a new instance of the class each time it is requested
    factory { LoginUseCase() }
    factory { GetUserDataUseCase() }

    // creating object of AuthViewModel
    viewModel { AuthViewModel(loginUseCase = get(), getUseCase = get()) }

    //creating scope testing class
    scope<MainActivity> { scoped{ScopeTestingClass()} }

    // retrofit request with koin
    single { ApiRepository(get()) }
    viewModel{ ApiViewModel(get()) }

}