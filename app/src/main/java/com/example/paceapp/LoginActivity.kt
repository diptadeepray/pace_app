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
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {


    private lateinit var binding: LoginActivityBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()


        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.loginButtonSubmit.setOnClickListener {
            val userEmail = binding.loginInputUsername.text.toString().trim()
            val userPassword = binding.loginInputPassword.text.toString().trim()

            // Validate inputs
            if (userEmail.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Sign in the user with Firebase Authentication
            auth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Check if the email is verified (optional)
                        if (auth.currentUser?.isEmailVerified == true) {
                            Toast.makeText(
                                this,
                                "Login successful! Welcome ${auth.currentUser?.email}",
                                Toast.LENGTH_SHORT
                            ).show()
                            // Redirect to home or dashboard activity
                            startActivity(Intent(this, MainActivity::class.java))
                            finish() // Close LoginActivity
                        } else {
                            Toast.makeText(
                                this,
                                "Please verify your email before logging in",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this,
                            "Login failed: ${task.exception?.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

        }

        binding.loginClickableTextRegistration.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        binding.loginClickableTextForgotPassword.setOnClickListener {
            //startActivity(Intent(this, ResetPasswordActivity::class.java))
            val email = binding.loginInputUsername.text.toString().trim()

            // Validate email input
            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your email address", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Send password reset email
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this,
                            "Password reset email sent. Please check your inbox.",
                            Toast.LENGTH_LONG
                        ).show()
                        // Redirect to LoginActivity
                        finish() // Closes ResetPasswordActivity
                    } else {
                        Toast.makeText(
                            this,
                            "Failed to send reset email: ${task.exception?.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }


    }}
