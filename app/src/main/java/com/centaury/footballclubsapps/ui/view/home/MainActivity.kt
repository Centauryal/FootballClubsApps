package com.centaury.footballclubsapps.ui.view.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.centaury.footballclubsapps.R
import com.centaury.footballclubsapps.ui.view.schedule.ScheduleFragment

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationSelected)

    }

    private val navigationSelected = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId){
            R.id.navigation_home -> {
                val mainFragment = MainFragment.newInstance()
                openFragment(mainFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_schedule -> {
                val scheduleFragment = ScheduleFragment.newInstance()
                openFragment(scheduleFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
