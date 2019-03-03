package com.yakymovych.simon.everywhere.ui.login

import androidx.lifecycle.Observer
import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import com.yakymovych.simon.everywhere.R
import com.yakymovych.simon.everywhere.databinding.ActivityLoginBinding
import com.yakymovych.simon.everywhere.ui.BaseActivity
import com.yakymovych.simon.everywhere.ui.BaseViewModel
import com.yakymovych.simon.everywhere.ui.main.MainActivity
import javax.inject.Inject

class LoginActivity : BaseActivity(){
    @Inject
    lateinit var loginViewModel: LoginViewModel

    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = loginViewModel


        loginViewModel.goToMainActivity.observe(this, Observer {
            it?.let { if(it) startMain() }
        })


    }

    private fun startMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}