package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
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
            toggle= ActionBarDrawerToggle(this,drawer, R.string.open,R.string.close)
            drawer.addDrawerListener(toggle)
            toggle.syncState()
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            val navigationView = findViewById<View>(R.id.navview) as NavigationView
            navigationView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.nav_user_seche -> {
                    //    Toast.makeText(this, "sting_1", Toast.LENGTH_SHORT).show()
                     supportFragmentManager?.beginTransaction()?.replace(R.id.fragment ,schedualFragment())?.commitNow()
                        setTitle("الجدول")
                        drawer.closeDrawers();
                    }
                    R.id.nav_about -> {
                     //   Toast.makeText(this, "sting_2", Toast.LENGTH_SHORT).show()
                        supportFragmentManager?.beginTransaction()?.replace(R.id.fragment,aboutFragment())?.commitNow()
                        setTitle("عن الكليه")
                        drawer.closeDrawers();
                    }
                    R.id.nav_inf -> {
                      //  Toast.makeText(this, "sting_3", Toast.LENGTH_SHORT).show()
                          supportFragmentManager?.beginTransaction()?.replace(R.id.fragment,inf_user_Fragment())?.commitNow()
                        setTitle("معلومات المستخدم")
                        drawer.closeDrawers();
                    }
                    R.id.nav_share -> {
                     //   Toast.makeText(this, "sting_4", Toast.LENGTH_SHORT).show()
                        supportFragmentManager?.beginTransaction()?.replace(R.id.fragment,shareFragment())?.commitNow()
                        setTitle("مشاركه")
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
}