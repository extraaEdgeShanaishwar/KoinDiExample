package com.example.demoapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.demoapp.R
import com.example.demoapp.databinding.ActivityMainBinding
import com.example.demoapp.di.authModule
import com.example.demoapp.viewmodel.ApiViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.activityScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinScopeComponent
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.scope.Scope

class MainActivity : AppCompatActivity() ,KoinScopeComponent {

    override val scope: Scope by activityScope()
    private lateinit var binding:ActivityMainBinding
    private val viewModel:AuthViewModel by viewModel()
    private val apiViewModel:ApiViewModel by viewModel()
    private val scopeTestingClass : ScopeTestingClass by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.executePendingBindings()

        loadKoinModules(authModule)

        binding.loginButton.setOnClickListener { viewModel.login() }
        binding.getUserTextButton.setOnClickListener { viewModel.getUser() }
        binding.cusotmeScopeBtn.setOnClickListener {  scopeTestingClass.scopeFunction() }

        binding.getCatFactBtn.setOnClickListener { apiViewModel.getCatFact() }

        apiViewModel.factData.observe(this){
            if (it.data!=null){
                binding.catFactTxtVal.text = it.data.fact
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(authModule)
    }
}