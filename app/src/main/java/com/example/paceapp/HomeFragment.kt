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

        val arrData=ArrayList<DataModel>()
        //Add Data
        arrData.add(DataModel("Yeh Yawani Hain Diwani","Ranbir"))
        arrData.add(DataModel("Wake Up Sid","Ranbir again"))


        // Sample data
        /*val data = listOf(
            DataModel("Title 1", "Subtitle 1"),
            DataModel("Title 2", "Subtitle 2"),
            DataModel("Title 3", "Subtitle 3"),
            DataModel("Title 4", "Subtitle 4")
        )*/



        // Find RecyclerView
        val recyclerView = binding.urgentNoteRecyclerView
            //view?.findViewById<RecyclerView>(R.id.recyclerView)


        // Set layout manager
        recyclerView.layoutManager = GridLayoutManager(requireActivity(),3)
            //LinearLayoutManager(this)

        // Set adapter
        recyclerView.adapter = RecyclerViewAdapter(arrData)


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





