package com.centaury.footballclubsapps.ui.view.home

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import com.centaury.footballclubsapps.R
import com.centaury.footballclubsapps.ui.view.pinned.PinnedFragment
import com.centaury.footballclubsapps.ui.view.schedule.ScheduleFragment
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private var menuItem: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainFragment = MainFragment.newInstance()
        openFragment(mainFragment)
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
            R.id.navigation_pinned -> {
                val pinnedFragment = PinnedFragment.newInstance()
                openFragment(pinnedFragment)
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

    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(R.id.container)
        val frags = frag.javaClass.simpleName
        if (fragmentManager.backStackEntryCount > 0){
            fragmentManager.popBackStack()
        } else{
            if (frags.equals("MainFragment")){
                alert("Are you sure want to exit application? ") {
                    yesButton {
                        finish()
                    }
                    noButton {

                    }
                }.show()
            } else{
                super.onBackPressed()
            }
        }
    }
}
