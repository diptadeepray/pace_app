package com.example.paceapp

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

import com.example.paceapp.databinding.AddClientBinding

class AddClient  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_client)

        // below we have created a new DBHelper class, and passed context to it
        val db = DBHelper(this, null)

        val c_name = findViewById<EditText>(R.id.input_client_full_name)
        val c_email = findViewById<EditText>(R.id.input_client_email_address)
        val c_phone= findViewById<EditText>(R.id.input_client_phone_number)
        val c_country = findViewById<EditText>(R.id.input_client_country)
        val c_referred = findViewById<EditText>(R.id.input_client_referred_by)
        val c_college = findViewById<EditText>(R.id.input_client_college_name)
        val c_due_payment = findViewById<EditText>(R.id.input_client_due_payment)
        val c_course_duration = findViewById<EditText>(R.id.input_client_course_duration)
        val c_course_name= findViewById<EditText>(R.id.input_client_course_name)

        val c_submit= findViewById<Button>(R.id.button_add_client)




        val cc_name = c_name.text.toString()
        val cc_email = c_email.text.toString()
        val cc_phone = c_phone.text.toString()
        val cc_country = c_country.text.toString()
        val cc_referred = c_referred.text.toString()
        val cc_college = c_college.text.toString()
        val cc_due_payment = c_due_payment.text.toString()
        val cc_course_duration = c_course_duration.text.toString()
        val cc_course_name = c_course_name.text.toString()



/*
        val sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        fun saveData(data1: String, data2: String, data3: String, data4: String, data5: String, data6: String,data7: String, data8: String, data9: String) {
            val editor = sharedPreferences.edit()
            editor.putString("Name", data1)
            editor.putString("Email", data2)
            editor.putString("Phone", data3)
            editor.putString("Country", data4)
            editor.putString("Refer", data5)
            editor.putString("College", data6)
            editor.putString("Payment", data7)
            editor.putString("Course Duration", data8)
            editor.putString("Course Name", data9)
            editor.apply()}*/




        c_submit.setOnClickListener() {






            // calling method to add
            // name to our database
            db.addDetails(cc_name, cc_email,cc_phone,cc_country,
                cc_referred,cc_college,cc_due_payment,cc_course_duration,cc_course_name)

            // Toast to message on the screen
            Toast.makeText(this, cc_name + " added to database", Toast.LENGTH_LONG).show()




            if (cc_name.isNotEmpty() && cc_course_name.isNotEmpty() && cc_phone.isNotEmpty()) {
                //saveData(cc_name,cc_email,cc_phone,cc_country,cc_referred,cc_college,cc_due_payment,cc_course_duration,cc_course_name)
                c_name.text.clear()
                c_email.text.clear()
                c_country.text.clear()
                c_referred.text.clear()
                c_college.text.clear()
                c_course_name.text.clear()
                c_phone.text.clear()
                c_due_payment.text.clear()
                c_course_duration.text.clear()

                finish()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }








    }
}
