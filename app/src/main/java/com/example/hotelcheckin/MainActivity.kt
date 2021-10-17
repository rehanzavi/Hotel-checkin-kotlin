package com.example.hotelcheckin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val checkButton = findViewById<Button>(R.id.btnCheckIn)
        checkButton.setOnClickListener{
            val intent = Intent(this, SendOTP::class.java)
            startActivity(intent)
        }
    }
}