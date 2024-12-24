package com.example.paceapp

import android.content.Intent

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.paceapp.databinding.ReportsFragmentBinding

import android.widget.Button
import com.example.paceapp.databinding.HomeFragmentBinding


class ReportsFragmentTL : Fragment(R.layout.reports_fragment) {

    private lateinit var binding: ReportsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ReportsFragmentBinding.inflate(inflater, container, false)

         binding.allClientSheet.setOnClickListener()
         {
             parentFragmentManager.beginTransaction()
                 .replace(R.id.fragment_container_view, ShowAllClients())
                 .addToBackStack(null)
                 .commit()
         }

        return binding.root}}