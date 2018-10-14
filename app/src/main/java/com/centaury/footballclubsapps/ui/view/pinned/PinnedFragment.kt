package com.centaury.footballclubsapps.ui.view.pinned

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.centaury.footballclubsapps.R
import com.centaury.footballclubsapps.data.model.pinned.Pinned
import com.centaury.footballclubsapps.ui.adapter.PinnedAdapter
import com.centaury.footballclubsapps.ui.view.detail.DetailScheduleActivity
import com.centaury.footballclubsapps.utils.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh

class PinnedFragment : Fragment() {

    private var pinneds: MutableList<Pinned> = mutableListOf()
    private lateinit var pinnedList: RecyclerView
    private lateinit var swipePinned: SwipeRefreshLayout
    private lateinit var adapter: PinnedAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_pinned, container, false)

        pinnedList = view.findViewById(R.id.recyc_pinned) as RecyclerView
        swipePinned = view.findViewById(R.id.swipepin) as SwipeRefreshLayout

        adapter = PinnedAdapter(pinneds){
            activity?.startActivity<DetailScheduleActivity>("detailEvent" to "${it.idEvent}")
        }
        pinnedList.layoutManager = LinearLayoutManager(activity)
        pinnedList.adapter = adapter
        showPinned()

        swipePinned.onRefresh {
            pinneds.clear()
            showPinned()
        }

        return view
    }

    private fun showPinned(){
        context?.database?.use {
            swipePinned.isRefreshing = false
            Log.d("idEvent:", "select")
            val result = select(Pinned.TABLE_PINNED)
            val pinned = result.parseList(classParser<Pinned>())
            Log.d("idEvent:", "select" + pinned.size)
            pinneds.addAll(pinned)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {
        fun newInstance(): PinnedFragment = PinnedFragment()
    }
}
