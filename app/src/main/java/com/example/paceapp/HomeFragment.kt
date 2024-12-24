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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView

import com.example.paceapp.databinding.HomeFragmentBinding


class HomeFragment : Fragment(R.layout.home_fragment) {

    private lateinit var binding: HomeFragmentBinding

   

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)

        //The data can be added here
        val arrDataUrgentNote=ArrayList<DataModel>()
        //Add Data
        arrDataUrgentNote.add(DataModel("Data Urgent Note 1"))
        arrDataUrgentNote.add(DataModel("Data Urgent Note 2"))
        arrDataUrgentNote.add(DataModel("Data Urgent Note 3"))
        arrDataUrgentNote.add(DataModel("Data Urgent Note 4"))


        //The data can be added here
        val arrDataPriorityPriority=ArrayList<DataModel>()
        //Add Data
        arrDataPriorityPriority.add(DataModel("Data Priority Priority 1"))
        arrDataPriorityPriority.add(DataModel("Data Priority Priority 2"))
        arrDataPriorityPriority.add(DataModel("Data Priority Priority 3"))
        arrDataPriorityPriority.add(DataModel("Data Priority Priority 4"))


        //The data can be added here
        val arrDataClientName=ArrayList<DataModel>()
        //Add Data
        arrDataClientName.add(DataModel("Data Client Name 1"))
        arrDataClientName.add(DataModel("Data Client Name 2"))
        arrDataClientName.add(DataModel("Data Client Name 3"))
        arrDataClientName.add(DataModel("Data Client Name 4"))


        // Find RecyclerView
        val recyclerViewUrgentNote = binding.urgentNoteRecyclerView
        val recyclerViewPriorityPayment = binding.priorityPaymentRecyclerView
        val recyclerViewClientName = binding.clientNameRecyclerView

        // Set layout manager
        recyclerViewUrgentNote.layoutManager = GridLayoutManager(requireActivity(),3)
        recyclerViewPriorityPayment.layoutManager = GridLayoutManager(requireActivity(),3)
        recyclerViewClientName.layoutManager = GridLayoutManager(requireActivity(),3)

        // Set adapter
        recyclerViewUrgentNote.adapter = RecyclerViewAdapter(arrDataUrgentNote)
        recyclerViewPriorityPayment.adapter = RecyclerViewAdapter(arrDataPriorityPriority)
        recyclerViewClientName.adapter = RecyclerViewAdapter(arrDataUrgentNote)






        binding.buttonManageTeam.setOnClickListener()
        {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, ManageYourTeam())
                .addToBackStack(null)
                .commit()

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
    }

}

  /*  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }*/





