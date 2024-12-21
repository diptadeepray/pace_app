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
import android.widget.EditText
import android.widget.Toast
import com.example.paceapp.AddTeamLead.TeamLead

import com.example.paceapp.databinding.AddClientBinding
import com.google.firebase.firestore.FirebaseFirestore

class AddClient : AppCompatActivity() {

    private lateinit var binding: AddClientBinding
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize binding and set the layout
        binding = AddClientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve user email from SharedPreferences
        val sharedPreferences = getSharedPreferences("PaceAppPrefs", Context.MODE_PRIVATE)
        val userEmail = sharedPreferences.getString("userEmail", null)

        if (userEmail != null) {
            Toast.makeText(this, "Welcome back, $userEmail!", Toast.LENGTH_SHORT).show()
        }

        // Set OnClickListener for the addClient button
        binding.buttonAddClient.setOnClickListener {

            // Get input field values
            val clientName = binding.inputClientFullName.text.toString().trim()
            val clientEmail = binding.inputClientEmailAddress.text.toString().trim()
            val clientPhone = binding.inputClientPhoneNumber.text.toString().trim()
            val clientCountry = binding.inputClientCountry.text.toString().trim()
            val clientReferredBy = binding.inputClientReferredBy.text.toString().trim()
            val clientCollegeName = binding.inputClientCollegeName.text.toString().trim()
            val clientDuePayment = binding.inputClientDuePayment.text.toString().trim()
            val clientCourseDuration = binding.inputClientCourseDuration.text.toString().trim()
            val clientCourseName = binding.inputClientCourseName.text.toString().trim()

            // Get checkbox states
            val isCheckbox1Checked = binding.checkFullCoursse.isChecked
            val isCheckbox2Checked = binding.checkNumberOfFullCourse.isChecked

            // Validate that at least one checkbox is selected
            if (!isCheckbox1Checked && !isCheckbox2Checked) {
                Toast.makeText(this, "Please select at least one type", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Determine the type of course selected
            val clientType = when {
                isCheckbox1Checked -> "Full Course"
                isCheckbox2Checked -> "Number of Full Courses"
                else -> "Unknown"
            }

            // Validate that all fields are filled
            if (clientName.isEmpty() || clientEmail.isEmpty() || clientPhone.isEmpty() ||
                clientCountry.isEmpty() || clientReferredBy.isEmpty() || clientCollegeName.isEmpty() ||
                clientDuePayment.isEmpty() || clientCourseDuration.isEmpty() || clientCourseName.isEmpty()
            ) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create a data object for the client details
            val clientDetails = ClientDetails(
                name = clientName,
                email = clientEmail,
                phone = clientPhone,
                country = clientCountry,
                referredBy = clientReferredBy,
                collegeName = clientCollegeName,
                duePayment = clientDuePayment,
                courseType = clientType,
                courseDuration = clientCourseDuration,
                courseName = clientCourseName,
                addedByAdmin = userEmail ?: "Unknown"
            )

            // Add client details to Firestore
            firestore.collection("ClientDetails")
                .add(clientDetails)
                .addOnSuccessListener {
                    Toast.makeText(this, "Client added successfully!", Toast.LENGTH_SHORT).show()
                    clearInputFields()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Failed to add client: ${e.message}", Toast.LENGTH_LONG).show()
                }
        }
    }

    // Clear all input fields after successful submission
    private fun clearInputFields() {
        binding.inputClientFullName.text.clear()
        binding.inputClientEmailAddress.text.clear()
        binding.inputClientPhoneNumber.text.clear()
        binding.inputClientCountry.text.clear()
        binding.inputClientReferredBy.text.clear()
        binding.inputClientCollegeName.text.clear()
        binding.inputClientDuePayment.text.clear()
        binding.inputClientCourseDuration.text.clear()
        binding.inputClientCourseName.text.clear()
        binding.checkFullCoursse.isChecked = false
        binding.checkNumberOfFullCourse.isChecked = false
    }

    // Data model class for ClientDetails
    data class ClientDetails(
        val name: String,
        val email: String,
        val phone: String,
        val country: String,
        val referredBy: String,
        val collegeName: String,
        val duePayment: String,
        val courseType: String,
        val courseDuration: String,
        val courseName: String,
        val addedByAdmin: String
    )
}

