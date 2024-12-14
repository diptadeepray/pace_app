package com.example.paceapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.paceapp.databinding.RegistrationActivityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: RegistrationActivityBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegistrationActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth and Firestore
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        binding.registrationClickableTextBackToLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.registrationButtonSubmit.setOnClickListener {
            val userName = binding.registrationInputName.text.toString().trim()
            val userEmail = binding.registrationInputEmail.text.toString().trim()
            val userPhone = binding.registrationInputPhone.text.toString().trim()
            val userPassword = binding.registrationInputPassword.text.toString().trim()
            val userConfirmPassword = binding.registrationInputConfirmPassword.text.toString().trim()

            // Validate input fields
            if (userName.isEmpty() || userEmail.isEmpty() || userPhone.isEmpty() ||
                userPassword.isEmpty() || userConfirmPassword.isEmpty()
            ) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (userPassword != userConfirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Register user with Firebase Authentication
            auth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userId = auth.currentUser?.uid
                        val user = hashMapOf(
                            "name" to userName,
                            "email" to userEmail,
                            "phone" to userPhone,
                            "userId" to userId
                        )

                        // Save user data to Firestore
                        firestore.collection("users").document(userId!!)
                            .set(user)
                            .addOnSuccessListener {
                                // Send email verification
                                auth.currentUser?.sendEmailVerification()
                                    ?.addOnCompleteListener { emailTask ->
                                        if (emailTask.isSuccessful) {
                                            Toast.makeText(
                                                this,
                                                "Registration successful. Please verify your email before logging in.",
                                                Toast.LENGTH_LONG
                                            ).show()
                                            startActivity(Intent(this, LoginActivity::class.java))
                                            finish()
                                        } else {
                                            Toast.makeText(
                                                this,
                                                "Failed to send verification email: ${emailTask.exception?.message}",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    }
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(
                                    this,
                                    "Failed to save user data: ${e.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                    } else {
                        Toast.makeText(
                            this,
                            "Registration failed: ${task.exception?.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }
}
