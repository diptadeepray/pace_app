package com.example.paceapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase.CursorFactory

import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext

class DBHelper(context: Context,
               factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object{

        private val DATABASE_NAME = "UserData.db"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "pace_table"

        // below is the variable for id column
        val ID_COL = "id"

        val NAME_COl = "name"
        val EMAIL_COL = "email"
        val PHONE_COl = "phone"
        val COUNTRY_COL = "country"
        val REFERRED_COl = "referred"
        val COLLEGE_COL = "college"
        val DUE_PAYMENT_COl = "due_payment"
        val COURSE_DURATION_COL = "course_duration"
        val COURSE_NAME= "course_name"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names
        // along with their data types is given
        val createTable = ("CREATE TABLE " + TABLE_NAME
                + ID_COL + " INTEGER PRIMARY KEY, " +
                NAME_COl + " TEXT," +
                EMAIL_COL + " TEXT" +
                PHONE_COl + " TEXT," +
                COUNTRY_COL + " TEXT" +
                REFERRED_COl + " TEXT," +
                COLLEGE_COL + " TEXT" +
                DUE_PAYMENT_COl + " TEXT," +
                COURSE_DURATION_COL + " TEXT" +
                COURSE_NAME+"TEXT")

        db.execSQL(createTable)
        }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    // This method is for adding data in our database
    fun addDetails(name: String, email: String,phone: String, country: String,
                   referred: String, college: String,due_payment: String, course_duration: String,
                   course_name: String
    ) {

        // here we are creating a
        // writable variable of
        // our database as we want to
        // insert value in our database
        val db = this.writableDatabase

        db.execSQL("INSERT INTO $TABLE_NAME ($NAME_COl, $EMAIL_COL,$PHONE_COl,$COUNTRY_COL,$REFERRED_COl, $COLLEGE_COL,$DUE_PAYMENT_COl,$COURSE_DURATION_COL,$COURSE_NAME) VALUES (?, ?)",
            arrayOf(name, email,phone,country,referred,college,due_payment,course_duration,course_name))



/*      // we are inserting our values
        // in the form of key-value pair
        values.put(NAME_COl, name)
        values.put(AGE_COL, age)

         // below we are creating
        // a content values variable
        val values = ContentValues()

        // all values are inserted into database
        db.insert(TABLE_NAME, null, values)*/



        // at last we are
        // closing our database
        db.close()
    }

    // below method is to get
    // all data from our database
    fun getName(): Cursor? {

        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase

        // below code returns a cursor to
        // read data from the database

        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

    }


}