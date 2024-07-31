package com.example.demoapp.base

import android.app.Application
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.demoapp.R
import com.example.demoapp.di.apiModule
import com.example.demoapp.di.authModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseActivity : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@BaseActivity)
            modules(modules = apiModule)
        }

    }

}