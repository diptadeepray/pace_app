package com.example.paceapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.content.Intent


import android.view.View
import android.view.ViewGroup

import android.widget.Button
import android.widget.TextView

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

import com.example.paceapp.databinding.HomeFragmentBinding


class HomeFragment : Fragment(R.layout.home_fragment) {

    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)


        binding.buttonManageTeam.setOnClickListener()
        {val intent=Intent(activity,ManageYourTeam::class.java)
            startActivity(intent)
        }

        binding.addNewTask.setOnClickListener()
        {val intent=Intent(activity,AddTask::class.java)
        startActivity(intent) }
        binding.addNewTeamLead.setOnClickListener()
        {val intent=Intent(activity,AddTeamLead::class.java)
            startActivity(intent) }
        binding.addNewClient.setOnClickListener()
        {val intent=Intent(activity,AddClient::class.java)
            startActivity(intent) }
        binding.addNewWriter.setOnClickListener()
        {val intent=Intent(activity,AddFreelancer::class.java)
            startActivity(intent) }

        return binding.root
    }}

  /*  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }*/





