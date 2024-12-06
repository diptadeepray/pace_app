package com.example.paceapp

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

import  com.example.paceapp.databinding.ShowAllClientBinding


import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class ShowAllClients : Fragment(R.layout.show_all_client) {
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var binding: ShowAllClientBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                          savedInstanceState: Bundle?): View? {

        binding =ShowAllClientBinding.inflate(inflater, container, false)




        val sharedPreferences = requireActivity().getSharedPreferences("UserData",AppCompatActivity.MODE_PRIVATE)


      /*  val data1 = sharedPreferences.getString("data1", "No Data")
        val data2 = sharedPreferences.getString("data2", "No Data")
        val data3 = sharedPreferences.getString("data3", "No Data")

        val displayText = "Data 1: $data1\nData 2: $data2\nData 3: $data3"
        textView.text = displayText*/

        val client_name=sharedPreferences.getString("Name","No Data")
        val client_email=sharedPreferences.getString("Email","No Data")
        val client_phone=sharedPreferences.getString("Phone","No Data")
        val client_country=sharedPreferences.getString("Country","No Data")
        val client_referred=sharedPreferences.getString("Refer","No Data")
        val client_college=sharedPreferences.getString("college","No Data")
        val client_due_payment=sharedPreferences.getString("Payment","No Data")
        val client_course_duration=sharedPreferences.getString("Course Suration","No Data")
        val client_course_dname=sharedPreferences.getString("course NAmeName","No Data")

binding.showText.text="Name: $client_name\nEmail: $client_email\nPhone: $client_phone"

        return binding.root}


 /*   override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }*/

    }
