package com.centaury.footballclubsapps.utils

import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.View
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

fun BottomNavigationView.disableShiftMode() {
    val menuView = getChildAt(0) as BottomNavigationMenuView
    try {
        menuView.javaClass.getDeclaredField("mShiftingMode").also { shiftMode ->
            shiftMode.isAccessible = true
            shiftMode.setBoolean(menuView, false)
            shiftMode.isAccessible = false
        }
        for (i in 0 until menuView.childCount) {
            (menuView.getChildAt(i) as BottomNavigationItemView).also { item ->
                item.setShiftingMode(false)
                item.setChecked(item.itemData.isChecked)
            }
        }
    } catch (t: Throwable) {
        Log.e("BottomNavigationHelper", "Unable to get shift mode field", t)
    } catch (e: IllegalAccessException) {
        Log.e("BottomNavigationHelper", "Unable to change value of shift mode", e)
    }
}

fun BottomNavigationView.active(position: Int) {
    menu.getItem(position).isChecked = true
}

enum class BottomNavigationPosition(val position: Int, val id: Int) {
    HOME(0, R.id.navigation_home),
    SCHEDULE(1, R.id.navigation_schedule);
}

fun findNavigationPositionById(id: Int): BottomNavigationPosition = when (id) {
    BottomNavigationPosition.HOME.id -> BottomNavigationPosition.HOME
    BottomNavigationPosition.SCHEDULE.id -> BottomNavigationPosition.SCHEDULE
    else -> BottomNavigationPosition.HOME
}

fun BottomNavigationPosition.createFragment(): Fragment = when (this) {
    BottomNavigationPosition.HOME -> MainFragment.newInstance()
    BottomNavigationPosition.SCHEDULE -> ScheduleFragment.newInstance()
}

fun BottomNavigationPosition.getTag(): String = when (this) {
    BottomNavigationPosition.HOME -> MainFragment.TAG
    BottomNavigationPosition.SCHEDULE -> ScheduleFragment.TAG
}

fun FragmentManager.detach() {
    findFragmentById(R.id.container)?.also {
        beginTransaction().detach(it).commit()
    }
}

fun FragmentManager.attach(fragment: Fragment, tag: String) {
    if (fragment.isDetached) {
        beginTransaction().attach(fragment).commit()
    } else {
        beginTransaction().add(R.id.container, fragment, tag).commit()
    }
    // Set a transition animation for this transaction.
    beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit()
}