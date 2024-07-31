package com.example.demoapp.server

import android.util.Log

class FirstServer() {
    fun performLogin(): Boolean {
        Log.d("koin-DI", "performLogin: first server")
        return true
    }
}


class SecondServer() {
    fun getUser(): String {
        Log.d("koin-DI", "getUser: second server")
        return "second server test log d"
    }
}