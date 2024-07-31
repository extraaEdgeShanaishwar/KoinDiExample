package com.example.demoapp.domain.use_case

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginUseCase(): KoinComponent{

    private val authRepository : AuthRepository by inject()

     operator fun invoke () : Boolean{
        return authRepository.login("test","pass")
     }
 }