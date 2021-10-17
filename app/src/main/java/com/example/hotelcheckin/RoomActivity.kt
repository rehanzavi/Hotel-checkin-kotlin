package com.example.hotelcheckin

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class RoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var datein: String? = null
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        val btn = findViewById<Button>(R.id.Submit)
        val name = findViewById<EditText>(R.id.txtName)
        val address = findViewById<EditText>(R.id.txtaddress)
        val mobno = intent.getStringExtra("mobno")
        val rtype = intent.getStringExtra("roomNo")
        val c1 = Calendar.getInstance()
        val c2 = Calendar.getInstance()
        c2.add(Calendar.DATE, +1)
        val c3 = Calendar.getInstance()
        c3.add(Calendar.DATE, +2)
        findViewById<TextView>(R.id.txtmob).text = mobno
        val droplist = arrayOf(SimpleDateFormat("dd-MM-yyyy").format(c1.getTime()), SimpleDateFormat("dd-MM-yyyy").format(c2.getTime()), SimpleDateFormat("dd-MM-yyyy").format(c3.getTime()))
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, droplist)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        val spin = findViewById<Spinner>(R.id.spin)
        spin.adapter = adapter
        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (spin.selectedItemPosition == 1) {
                    datein = SimpleDateFormat("dd-MM-yyyy").format(c2.getTime())
                } else if (spin.selectedItemPosition == 2) {
                    datein = SimpleDateFormat("dd-MM-yyyy").format(c3.getTime())
                } else {
                    datein = SimpleDateFormat("dd-MM-yyyy").format(c1.getTime())
                }
            }


            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
        btn.setOnClickListener(){
            if(name.text !=null && address.text !=null ){
                val helper = MyHelper(applicationContext)
                val db = helper.writableDatabase
                var cv = ContentValues()
                cv.put("Name", name.text.toString())
                cv.put("Address", address.text.toString())
                cv.put("Phone", mobno.toString())
                cv.put("Rtype",rtype.toString())
                cv.put("Datein",datein.toString())
                val id: Long = db.insert("HCTable", null, cv)
                db.close()
                if (rtype == "1") {

                    assign(0,id)
                }
                else if (rtype == "2") {

                    assign(1,id)
                }
                else if (rtype == "3") {

                    assign(2,id)
                }

                }
            else
            {
                Toast.makeText(this, "PLease Fill All Fields", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun assign(i: Int, id: Long) {
        var roomno = 0
        val helper = MyHelper(applicationContext)
        val db = helper.readableDatabase
        if (i == 0) {
            for (j in 101..199) {
                val sql = "SELECT COUNT(*) FROM HCTable WHERE Room=$j"
                val c = db.rawQuery(sql, null)
                if (c != null){
                    c.moveToFirst()
                if (c.getInt(0) == 0) {
                    roomno = j
                    break
                }}
            }
        }
        if (i == 1) {
            for (j in 201..299) {
                val sql = "SELECT COUNT(*) FROM HCTable WHERE Room=$j"
                val c = db.rawQuery(sql, null)
                if (c != null){
                    c.moveToFirst()
                if (c.getInt(0) == 0) {
                    roomno = j
                    break
                }}
            }
        }
        if (i == 2) {
            for (j in 301..399) {
                val sql = "SELECT COUNT(*) FROM HCTable WHERE Room=$j"
                val c = db.rawQuery(sql, null)
                if (c != null){
                    c.moveToFirst()
                if (c.getInt(0) == 0) {
                    roomno = j
                    break
                }
            }
        }}
        val strSQL = "UPDATE HCTable SET Room=$roomno WHERE Id=$id"
        db.execSQL(strSQL)
        val intent = Intent(this, FinalActivity::class.java)
        intent.putExtra("roomno", roomno.toString())
        startActivity(intent)
        finish()

    }
}





