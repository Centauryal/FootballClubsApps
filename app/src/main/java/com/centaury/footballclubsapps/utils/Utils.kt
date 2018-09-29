package com.centaury.footballclubsapps.utils

import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.centaury.footballclubsapps.R
import com.centaury.footballclubsapps.ui.view.home.MainFragment
import com.centaury.footballclubsapps.ui.view.schedule.ScheduleFragment

/**
 * Created by Centaury on 23/09/2018.
 */
fun View.visible(){
    visibility = View.VISIBLE
}

fun View.invisible(){
    visibility = View.INVISIBLE
}