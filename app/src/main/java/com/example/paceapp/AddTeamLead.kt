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
import com.example.paceapp.databinding.AddTeamLeadBinding
import com.example.paceapp.databinding.LoginActivityBinding
import com.google.firebase.firestore.FirebaseFirestore

class AddTeamLead : AppCompatActivity() {

    private lateinit var binding: AddTeamLeadBinding
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize binding and bind the specific XML layout
        binding = AddTeamLeadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("PaceAppPrefs", Context.MODE_PRIVATE)
        val userEmail = sharedPreferences.getString("userEmail", null)
        if (userEmail != null) {
            Toast.makeText(this, "Welcome back, $userEmail!", Toast.LENGTH_SHORT).show()
        }

        // Access views directly using binding
        binding.buttonAddTeamLead.setOnClickListener {
            val teamLeadName = binding.inputTeamLeadFullName.text.toString().trim()
            val teamLeadEmail = binding.inputTeamLeadEmailAddress.text.toString().trim()
            val teamLeadPhone = binding.inputTeamLeadPhoneNumber.text.toString().trim()
            val teamLeadAddress = binding.inputTeamLeadAddress.text.toString().trim()
            val teamLeadWhatsApp = binding.inputTeamLeadWhatsappNumber.text.toString().trim()

            // Validate inputs
            if (teamLeadName.isEmpty() || teamLeadEmail.isEmpty() || teamLeadPhone.isEmpty() ||
                teamLeadAddress.isEmpty() || teamLeadWhatsApp.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create a data object
            val teamLeadDetails = TeamLead(
                name = teamLeadName,
                email = teamLeadEmail,
                phone = teamLeadPhone,
                address = teamLeadAddress,
                whatsapp = teamLeadWhatsApp,
                addedByAdmin = userEmail.toString()
            )

            // Add data to Firestore
            firestore.collection("TeamLeads")
                .add(teamLeadDetails)
                .addOnSuccessListener {
                    Toast.makeText(this, "Team Lead added successfully!", Toast.LENGTH_SHORT).show()
                    clearInputFields()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Failed to add Team Lead: ${e.message}", Toast.LENGTH_LONG).show()
                }
        }
    }

    private fun clearInputFields() {
        binding.inputTeamLeadFullName.text.clear()
        binding.inputTeamLeadEmailAddress.text.clear()
        binding.inputTeamLeadPhoneNumber.text.clear()
        binding.inputTeamLeadAddress.text.clear()
        binding.inputTeamLeadWhatsappNumber.text.clear()
    }

    data class TeamLead(
        val name: String,
        val email: String,
        val phone: String,
        val address: String,
        val whatsapp: String,
        val addedByAdmin: String
    )
}