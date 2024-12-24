package com.example.paceapp

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.Button
import android.widget.Toast
import com.example.paceapp.AddTeamLead.TeamLead
import com.example.paceapp.databinding.AddFreelancerBinding
import com.example.paceapp.databinding.AddTeamLeadBinding
import com.google.firebase.firestore.FirebaseFirestore

class AddFreelancer : AppCompatActivity() {

    private lateinit var binding: AddFreelancerBinding
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Initialize binding and bind the specific XML layout
        binding = AddFreelancerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("PaceAppPrefs", Context.MODE_PRIVATE)
        val userEmail = sharedPreferences.getString("userEmail", null)
        if (userEmail != null) {
            Toast.makeText(this, "Welcome back, $userEmail!", Toast.LENGTH_SHORT).show()
        }

        // Access views directly using binding
        binding.buttonAddFreelancer.setOnClickListener {
            val freelancerName = binding.inputFreelancerFullName.text.toString().trim()
            val freelancerEmail = binding.inputFreelancerEmailAddress.text.toString().trim()
            val freelancerPhone = binding.inputFreelancerPhoneNumber.text.toString().trim()
            val freelancerAddress = binding.inputFreelancerAddress.text.toString().trim()
            val freelancerWhatsApp = binding.inputFreelancerWhatsappNumber.text.toString().trim()
            val freelancerTeamLeadName = binding.inputFreelancerTeamLeadName.text.toString().trim()

            // Validate inputs
            if (freelancerName.isEmpty() || freelancerEmail.isEmpty() || freelancerPhone.isEmpty() ||
                freelancerAddress.isEmpty() || freelancerWhatsApp.isEmpty() ||
                freelancerTeamLeadName.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create a data object
            val freelancerDetails = Freelancer(
                name = freelancerName,
                email = freelancerEmail,
                phone = freelancerPhone,
                address = freelancerAddress,
                whatsapp = freelancerWhatsApp,
                teamLeadName = freelancerTeamLeadName,
                addedByAdmin = userEmail.toString()
            )

            // Add data to Firestore
            firestore.collection("Freelancer")
                .add(freelancerDetails)
                .addOnSuccessListener {
                    Toast.makeText(this, "Freelancer added successfully!", Toast.LENGTH_SHORT).show()
                    clearInputFields()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Failed to add Freelancer: ${e.message}", Toast.LENGTH_LONG).show()
                }
        }


    }


    private fun clearInputFields() {
        binding.inputFreelancerFullName.text.clear()
        binding.inputFreelancerEmailAddress.text.clear()
        binding.inputFreelancerPhoneNumber.text.clear()
        binding.inputFreelancerAddress.text.clear()
        binding.inputFreelancerWhatsappNumber.text.clear()
        binding.inputFreelancerTeamLeadName.text.clear()
    }

    data class Freelancer(
        val name: String,
        val email: String,
        val phone: String,
        val address: String,
        val whatsapp: String,
        val teamLeadName: String,
        val addedByAdmin: String
    )
}