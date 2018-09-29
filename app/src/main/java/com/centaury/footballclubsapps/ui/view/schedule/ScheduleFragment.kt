package com.centaury.footballclubsapps.ui.view.schedule

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.centaury.footballclubsapps.R

class ScheduleFragment : Fragment() {

    private lateinit var scheduleTabAdapter: ScheduleTabAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_schedule, container, false)

        viewPager = view.findViewById(R.id.content_container_schedule) as ViewPager
        tabLayout = view.findViewById(R.id.tab_schedule) as TabLayout

        scheduleTabAdapter = ScheduleTabAdapter(childFragmentManager)
        viewPager.adapter = scheduleTabAdapter
        tabLayout.setupWithViewPager(viewPager)

        return view
    }

    private class ScheduleTabAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment? {
            var fragment: Fragment? = null
            if (position == 0){
                fragment = LastScheduleFragment()
            } else if (position == 1){
                fragment = NextScheduleFragment()
            }

            return fragment
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            var title: String? = null
            if (position == 0){
                title = "Last Match"
            } else if (position == 1){
                title = "Next Match"
            }
            return title
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {
        fun newInstance(): ScheduleFragment = ScheduleFragment()
    }
}
