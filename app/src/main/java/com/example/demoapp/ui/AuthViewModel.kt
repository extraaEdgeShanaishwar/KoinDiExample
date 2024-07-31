package com.example.demoapp.ui

import androidx.lifecycle.ViewModel
import com.example.demoapp.domain.use_case.GetUserDataUseCase
import com.example.demoapp.domain.use_case.LoginUseCase

class AuthViewModel(private val loginUseCase: LoginUseCase , private val getUseCase: GetUserDataUseCase):ViewModel(){
    fun login(){
        loginUseCase()
    }

    fun getUser(){
        getUseCase()
    }
}