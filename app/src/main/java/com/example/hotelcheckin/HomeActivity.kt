package com.example.hotelcheckin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val room1 = findViewById<ImageView>(R.id.imgg1)
        val room2 = findViewById<ImageView>(R.id.imgg2)
        val room3 = findViewById<ImageView>(R.id.imgg3)
        val mobno = intent.getStringExtra("mobno").toString()

        room1.setOnClickListener(){
            login(1,mobno)
        }
        room2.setOnClickListener(){
            login(2,mobno)
        }
        room3.setOnClickListener(){
            login(3,mobno)
        }

    }

    private fun login(i: Int, mobno: String) {
        val intent = Intent(this, RoomActivity::class.java)
        intent.putExtra("roomNo", i.toString())
        intent.putExtra("mobno", mobno)
        startActivity(intent)

    }

}