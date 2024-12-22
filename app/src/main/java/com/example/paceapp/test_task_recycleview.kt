package com.example.paceapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class TestTaskRecycleView : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private val userList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_task_recycleview)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        userAdapter = UserAdapter(userList)
        recyclerView.adapter = userAdapter

        fetchUsersFromFirebase()
    }

    private fun fetchUsersFromFirebase() {
        val db = FirebaseFirestore.getInstance()
        db.collection("Freelancer")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val user = document.toObject(User::class.java)
                    userList.add(user)
                }
                userAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                // Handle error
            }
    }
}
