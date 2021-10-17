package com.example.hotelcheckin

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class MyHelper(context: Context) :
        SQLiteOpenHelper(context,"HCDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE HCTable (Id Integer PRIMARY KEY, Name TEXT, Datein TEXT, Address TEXT, Room Integer, Phone TEXT, rtype TEXT)"
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }


}

