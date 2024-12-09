//Problems:
//Only 2 columns are created as name and age
//Only 1 data is getting saved


package com.example.paceapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.provider.ContactsContract.CommonDataKinds.Email

import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext

class DBHelper(var context: Context,

               factory: SQLiteDatabase.CursorFactory?):
               //You can delete this line, so there will be factory instead of null

    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private val DATABASE_NAME = "UserData"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "pace_table"

        //primary key
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

    val createTable:String = "CREATE TABLE $TABLE_NAME ($ID_COL INTEGER PRIMARY KEY AUTOINCREMENT, $NAME_COl TEXT, $EMAIL_COL TEXT, $PHONE_COl TEXT)"


    override fun onCreate(db: SQLiteDatabase)
    {
        db?.execSQL(createTable)
    }

@Override
    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        db?.execSQL(createTable)
    }

    // This method is for adding data in our database
    fun addDetails(name: String, email: String,phone: String,/* country: String,
                   referred: String, college: String,due_payment: String, course_duration: String,
                   course_name: String*/):Long {

        // here we are creating a writable variable of our database as we want to insert value in our database
        val  db:SQLiteDatabase = this.writableDatabase

        val values = ContentValues()
        values.put(EMAIL_COL,email)
            values.put(NAME_COl,name)
        values.put(PHONE_COl,phone)


        val result:Long = db.insert(TABLE_NAME, null, values)
        return result

        /*
        db.execSQL("INSERT INTO $TABLE_NAME ($NAME_COl, $EMAIL_COL" +
                ,$PHONE_COl,$COUNTRY_COL,$REFERRED_COl, $COLLEGE_COL,$DUE_PAYMENT_COl,$COURSE_DURATION_COL,$COURSE_NAME) VALUES (?, ?)",
            arrayOf(name, email,phone,country,referred,college,due_payment,course_duration,course_name))*/



    }

    // below method is to get
    // all data from our database
    fun getName(): String {

        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase

        // below code returns a cursor to
        // read data from the database

        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val stringBuilder = StringBuilder()


        var any = try {
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndexOrThrow(ID_COL))
                    val email = cursor.getString(cursor.getColumnIndexOrThrow(EMAIL_COL))
                    val name = cursor.getString(cursor.getColumnIndexOrThrow(NAME_COl))
                    val phone = cursor.getString(cursor.getColumnIndexOrThrow(NAME_COl))

                    stringBuilder.append("ID: $id, Name: $name, Email: $email,Phonw: $phone")
                } while (cursor.moveToNext())
            } else {
                stringBuilder.append("No data found")
            }
        }
        catch (e: Exception) {
            stringBuilder.append("Error: ${e.message}")}
        cursor.close()
        db.close()
        return stringBuilder.toString()
    }


}
