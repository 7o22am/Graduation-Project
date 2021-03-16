package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R.layout.activity_l_o_g_i_n
import android.widget.Button
import android.widget.Toast

class LOGIN : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_l_o_g_i_n)
        getSupportActionBar()?.hide();
    /*    val myButton = findViewById<Button>(R.id.register) as Button
        val myButton2 = findViewById<Button>(R.id.login) as Button
        myButton.setOnClickListener(){
           // supportFragmentManager.beginTransaction().replace(R.id.,registe()).commitNow()
        }
        myButton2.setOnClickListener(){
            Toast.makeText(this,"button 2 clicked", Toast.LENGTH_SHORT).show()
        }*/

        var backe_share : SharedPreferences =  getSharedPreferences("remimber", 0)
        var bb =  backe_share.getString("stata","")

if(bb == "active")
{
    startActivity(Intent(this,MainActivity::class.java))
}
    }
    override fun onBackPressed() {
        //  super.onBackPressed()
        supportFragmentManager?.beginTransaction()?.replace(R.id.fragment, loginFragment())?.commitNow()
    }
}