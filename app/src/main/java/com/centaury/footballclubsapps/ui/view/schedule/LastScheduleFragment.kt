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
import com.centaury.footballclubsapps.data.model.lastschedule.EventsItem
import com.centaury.footballclubsapps.data.service.ApiRepository
import com.centaury.footballclubsapps.ui.adapter.LastScheduleAdapter
import com.google.gson.Gson
import org.jetbrains.anko.support.v4.onRefresh

class LastScheduleFragment : Fragment(), LastScheduleView {

    private var events: MutableList<EventsItem> = mutableListOf()

    private lateinit var listLastSchedule: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var lastScheduleAdapter: LastScheduleAdapter
    private lateinit var presenterLast: LastSchedulePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_last_schedule, container, false)

        listLastSchedule = view.findViewById(R.id.recyc_last) as RecyclerView
        swipeRefresh = view.findViewById(R.id.swipelast) as SwipeRefreshLayout

        lastScheduleAdapter = LastScheduleAdapter(events)
        listLastSchedule.layoutManager = LinearLayoutManager(context)
        listLastSchedule.adapter = lastScheduleAdapter

        val request = ApiRepository()
        val gson = Gson()

        presenterLast = LastSchedulePresenter(this, request, gson)
        presenterLast.getLastMatchList()

        swipeRefresh.onRefresh {
            presenterLast.getLastMatchList()
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()

    }

    override fun showTeamList(data: List<EventsItem>) {
        swipeRefresh.isRefreshing = false
        events.clear()
        events.addAll(data)
        lastScheduleAdapter.notifyDataSetChanged()
    }

    companion object {

    }
}
