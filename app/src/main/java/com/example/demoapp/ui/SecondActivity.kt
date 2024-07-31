package com.example.demoapp.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.demoapp.R
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.activityScope
import org.koin.core.component.KoinScopeComponent
import org.koin.core.scope.Scope
import kotlin.math.log

class SecondActivity : AppCompatActivity(),KoinScopeComponent
{

    override val scope: Scope by activityScope()
    private val scopeTestingClass : ScopeTestingClass by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

//        scopeTestingClass.scopeFunction()

    }
}

class ScopeTestingClass {
    fun scopeFunction(){
        println("im injected properly")
    }
}