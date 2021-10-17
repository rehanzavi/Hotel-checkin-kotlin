package com.example.hotelcheckin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.lang.System.exit
import kotlin.system.exitProcess

class FinalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)
        val roomno = intent.getStringExtra("roomno")
        val txt = findViewById<TextView>(R.id.room)
        txt.text = roomno.toString()
        val btnmore = findViewById<Button>(R.id.btnMore)
        btnmore.setOnClickListener(){
            finish()
            exitProcess(0)
        }
        findViewById<Button>(R.id.exit).setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}