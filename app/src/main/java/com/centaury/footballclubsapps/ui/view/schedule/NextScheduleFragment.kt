package com.centaury.footballclubsapps.ui.view.schedule

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.centaury.footballclubsapps.R
import com.centaury.footballclubsapps.data.model.nextschedule.NextItem
import com.centaury.footballclubsapps.data.service.ApiRepository
import com.centaury.footballclubsapps.ui.adapter.NextScheduleAdapter
import com.google.gson.Gson
import org.jetbrains.anko.support.v4.onRefresh

class NextScheduleFragment : Fragment(), NextScheduleView {

    private var events: MutableList<NextItem> = mutableListOf()

    private lateinit var listLastSchedule: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var nextscheduleAdapter: NextScheduleAdapter
    private lateinit var presenterNext: NextSchedulePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_next_schedule, container, false)

        listLastSchedule = view.findViewById(R.id.recyc_next) as RecyclerView
        swipeRefresh = view.findViewById(R.id.swipenext) as SwipeRefreshLayout

        nextscheduleAdapter = NextScheduleAdapter(events)
        listLastSchedule.layoutManager = LinearLayoutManager(context)
        listLastSchedule.adapter = nextscheduleAdapter

        val request = ApiRepository()
        val gson = Gson()

        presenterNext = NextSchedulePresenter(this, request, gson)
        presenterNext.getNextMatchList()

        swipeRefresh.onRefresh {
            presenterNext.getNextMatchList()
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun showNextScheduleList(data: List<NextItem>) {
        swipeRefresh.isRefreshing = false
        events.clear()
        events.addAll(data)
        nextscheduleAdapter.notifyDataSetChanged()
    }

    companion object {
    }
}
