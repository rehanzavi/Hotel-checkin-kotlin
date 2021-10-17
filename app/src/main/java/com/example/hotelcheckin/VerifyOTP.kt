package com.example.hotelcheckin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.lang.Integer.*


class VerifyOTP : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth=FirebaseAuth.getInstance()
        val mob1 = intent.getStringExtra("mob")
        setContentView(R.layout.activity_verifyotp)
        val otpGiven = findViewById<EditText>(R.id.etInput1)
        val verify1=findViewById<Button>(R.id.btnVerify)
        val mob = findViewById<TextView>(R.id.txtno)

        val storedVerificationId=intent.getStringExtra("storedVerificationId")

        val resend = findViewById<TextView>(R.id.txtresend)
        mob.text=mob1

        resend.setOnClickListener(){
            val rintent = Intent(this, SendOTP::class.java)
            startActivity(rintent)
            finish()
        }

        verify1.setOnClickListener{

            var otp=otpGiven.text.toString().trim()
            if(!otp.isEmpty()){
                val credential : PhoneAuthCredential = PhoneAuthProvider.getCredential(
                    storedVerificationId.toString(), otp)
                signInWithPhoneAuthCredential(credential)
            }else{
                Toast.makeText(this,"Enter OTP",Toast.LENGTH_SHORT).show()
            }
        }
        }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val rr = intent.getStringExtra("mob").toString()
                    val helper = MyHelper(applicationContext)
                    val db = helper.readableDatabase
                    val sql = "SELECT count(*) FROM HCTable WHERE Phone=$rr"
                    val c = db.rawQuery(sql, null)
                        c.moveToFirst()
                    if (c.getInt(0) != 0){
                       val intent1 = Intent(this, DataActivity::class.java)
                        intent1.putExtra("mobno",intent.getStringExtra("mob"))
                        startActivity(intent1)
                        finish()
                        }
                    else{
                    val intent1 = Intent(this, HomeActivity::class.java)
                    intent1.putExtra("mobno", intent.getStringExtra("mob"))
                    startActivity(intent1)
                    finish()
                        }
// ...
                } else {
// Sign in failed, display a message and update the UI
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
// The verification code entered was invalid
                        Toast.makeText(this,"Invalid OTP",Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }
}




