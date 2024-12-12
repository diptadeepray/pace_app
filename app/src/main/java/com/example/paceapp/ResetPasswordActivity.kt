package com.example.paceapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.view.View

import android.content.Intent

//This import is used, so that we can use ViewBinding
//i.e. use objects of the layouts instead of findViewById
import com.example.paceapp.databinding.LoginActivityBinding

//Importing this will not do the job
import androidx.fragment.app.Fragment

//We need to import the following
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

import android.widget.Button
import android.widget.TextView
import com.example.paceapp.databinding.ResetPasswordActivityBinding


class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ResetPasswordActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()



        binding = ResetPasswordActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.resetPasswordClickableTextBackToLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.resetPasswordClickableTextBackToRegistration.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }



        binding.resetPasswordButtonOldPassword.setOnClickListener {
            // code can be written here
        }
        binding.resetPasswordButtonNewPassword.setOnClickListener {
            // code can be written here
        }
        binding.resetPasswordButtonConfirmNewPassword.setOnClickListener {
            // code can be written here
        }
        binding.resetPasswordButtonSubmit.setOnClickListener {
            // code can be written here
        }

    }}