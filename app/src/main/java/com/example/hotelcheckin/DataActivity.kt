package com.example.hotelcheckin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.TextView

class DataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rm: MutableList<String> = ArrayList()
        val dt: MutableList<String> = ArrayList()
        setContentView(R.layout.activity_data)
        val tv1=findViewById<TextView>(R.id.textView1)
        val tv2=findViewById<TextView>(R.id.textView2)
        val btn = findViewById<Button>(R.id.button2)
        val mobno = intent.getStringExtra("mobno").toString()
        val helper = MyHelper(applicationContext)
        val db = helper.readableDatabase
        val rs = db.rawQuery("SELECT * FROM HCTable WHERE Phone=$mobno",null)
        if(rs!=null){
            rs.moveToFirst()
            do{
                val room = rs.getString(rs.getColumnIndex("Room"))
                val datein = rs.getString(rs.getColumnIndex("Datein"))
                rm.add(room)
                dt.add(datein)
            }while(rs.moveToNext())
        }
        var rms: String? = ""
        val i:Int
        for(i in rm){
            rms = "$rms$i\n"
        }
        var dts: String? = ""

        for(i in dt){
            dts = "$dts$i\n"
        }

        tv1.text = rms
        tv2.text = dts

        btn.setOnClickListener(){
           val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("mobno", mobno)
            startActivity(intent)
            finish()
        }
    }
}