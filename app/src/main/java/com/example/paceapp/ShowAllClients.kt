package com.example.paceapp

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import  com.example.paceapp.databinding.ShowAllClientBinding

import android.widget.Toast

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.paceapp.R

import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class ShowAllClients : Fragment(R.layout.show_all_client) {
    //private lateinit var sharedPreferences: SharedPreferences

    //private lateinit var database: SQLiteDatabase
    private lateinit var dbHelper: DBHelper

    private lateinit var binding: ShowAllClientBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                          savedInstanceState: Bundle?): View? {

        binding =ShowAllClientBinding.inflate(inflater, container, false)

        //requireActivity().openOrCreateDatabase("GEEKS_FOR_GEEKS", android.content.Context.MODE_PRIVATE, null)
        //database.execSQL("CREATE TABLE IF NOT EXISTS data (name TEXT, age INTEGER, city TEXT)")



        dbHelper = DBHelper(requireContext(), null)
        val db = dbHelper.getName()
        val txt=binding.showText
        txt.text=db

        //txt.append(db)



        // val nameBuilder = StringBuilder()

        // below is the variable for cursor
        // we have called method to get
        // all names from our database
        // and add to name text view
        //val cursor = db.getName()

        /*cursor!!.moveToFirst()
        txt.append(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.NAME_COl)) + "\n")
        txt.append(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.AGE_COL)) + "\n")

        // moving our cursor to next
        // position and appending values
        while(cursor.moveToNext()){
            txt.append(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.NAME_COl)) + "\n")
            txt.append(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.AGE_COL)) + "\n")
        }*/
/*
        if (db != null /*&& db.moveToFirst()*/) {
            do {
                //txt.append(db.getString(db.getColumnIndexOrThrow(DBHelper.NAME_COL)) + "\n")
                /*txt.append(db.getString(db.getColumnIndexOrThrow(DBHelper.NAME_COl)) + "\n")
                txt.append(db.getString(db.getColumnIndexOrThrow(DBHelper.EMAIL_COL)) + "\n")
                txt.append(db.getString(db.getColumnIndexOrThrow(DBHelper.PHONE_COl)) + "\n")
                txt.append(db.getString(db.getColumnIndexOrThrow(DBHelper.COUNTRY_COL)) + "\n")
                txt.append(db.getString(db.getColumnIndexOrThrow(DBHelper.REFERRED_COl)) + "\n")
                txt.append(db.getString(db.getColumnIndexOrThrow(DBHelper.COLLEGE_COL)) + "\n")
                txt.append(db.getString(db.getColumnIndexOrThrow(DBHelper.DUE_PAYMENT_COl)) + "\n")
                txt.append(db.getString(db.getColumnIndexOrThrow(DBHelper.COURSE_DURATION_COL)) + "\n")
                txt.append(db.getString(db.getColumnIndexOrThrow(DBHelper.COURSE_NAME)) + "\n")*/
            } while (db.moveToNext())
        }*/




// at last we close our cursor
        //cursor?.close()




/*

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

binding.showText.text="Name: $client_name\nEmail: $client_email\nPhone: $client_phone"*/

        return binding.root}


 /*   override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }*/

    }
