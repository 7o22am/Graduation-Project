package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            setUpNavigationDrawer()


    }
        private fun setUpNavigationDrawer()
        {

            val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
            toggle= ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
            drawer.addDrawerListener(toggle)
            toggle.syncState()
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            val navigationView = findViewById<View>(R.id.navview) as NavigationView
            navigationView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.nav_user_seche -> {
                        //    Toast.makeText(this, "sting_1", Toast.LENGTH_SHORT).show()
                        supportFragmentManager?.beginTransaction()?.replace(R.id.fragment, schedualFragment())?.commitNow()
                        setTitle("schedule")
                        drawer.closeDrawers();
                    }
                    R.id.nav_about -> {
                        //   Toast.makeText(this, "sting_2", Toast.LENGTH_SHORT).show()
                        supportFragmentManager?.beginTransaction()?.replace(R.id.fragment, aboutFragment())?.commitNow()
                        setTitle("About")
                        drawer.closeDrawers();
                    }
                    R.id.nav_inf -> {
                        //  Toast.makeText(this, "sting_3", Toast.LENGTH_SHORT).show()
                        supportFragmentManager?.beginTransaction()?.replace(R.id.fragment, inf_user_Fragment())?.commitNow()
                        setTitle("Profile ")
                        drawer.closeDrawers();
                    }
                    R.id.nav_share -> {
                        /* Toast.makeText(this, "stingkkkkkkkkkkkkkkkkkkk", Toast.LENGTH_SHORT).show()*/
                        val sent = Intent()
                        sent.action = Intent.ACTION_SEND
                        sent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.example.myapplication")
                        sent.type = "text/plan"
                        startActivity(Intent.createChooser(sent, "اختر التطبيق الذي تريد المشاركه معه :"))
                        drawer.closeDrawers();
                        setTitle("share")
                    }
                    R.id.nav_location -> {
                        supportFragmentManager?.beginTransaction()?.replace(R.id.fragment, MapsFragment())?.commitNow()
                        setTitle("location")
                        drawer.closeDrawers();
                    }
                    R.id.nav_log_out -> {
                        var share: SharedPreferences? = null
                        share = getSharedPreferences("remimber", 0)
                        var edit: SharedPreferences.Editor = share?.edit()!!
                        edit.putString("stata", "notactive")
                        edit.commit()
                        startActivity(Intent(this, LOGIN::class.java))
                        drawer.closeDrawers();
                    }
                    R.id.nav_send_feedback -> {
                        supportFragmentManager?.beginTransaction()?.replace(R.id.fragment, send_feedback_Fragment())?.commitNow()
                        setTitle("Feedback")
                        drawer.closeDrawers();
                    }

                    R.id.nav_alerts -> {
                        supportFragmentManager?.beginTransaction()?.replace(R.id.fragment, Alerts_Fragment())?.commitNow()
                        setTitle("Alerts")
                        drawer.closeDrawers();
                    }

                }
                true
            }

        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
        {
            return true
        }
        return super.onOptionsItemSelected(item)



    }

    override fun onBackPressed() {
      //  super.onBackPressed()
        supportFragmentManager?.beginTransaction()?.replace(R.id.fragment, schedualFragment())?.commitNow()
    }
}