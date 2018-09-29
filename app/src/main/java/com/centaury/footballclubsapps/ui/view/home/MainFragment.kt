package com.centaury.footballclubsapps.ui.view.home

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.centaury.footballclubsapps.R

import com.centaury.footballclubsapps.R.array.league
import com.centaury.footballclubsapps.R.color.colorAccent
import com.centaury.footballclubsapps.data.model.league.TeamsItem
import com.centaury.footballclubsapps.data.service.ApiRepository
import com.centaury.footballclubsapps.ui.adapter.LeagueAdapter
import com.centaury.footballclubsapps.utils.*
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MainFragment : Fragment(), MainView {

    private var teams: MutableList<TeamsItem> = mutableListOf()

    private lateinit var presenter: MainPresenter
    private lateinit var adapter: LeagueAdapter
    private lateinit var leagueName: String

    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        

        adapter = LeagueAdapter(teams)
        listTeam.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        val spinnerItems = resources.getStringArray(league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                presenter.getTeamList(leagueName)
            }
        }

        swipeRefresh.onRefresh {
            presenter.getTeamList(leagueName)
        }

        return UI {
            verticalLayout {
                lparams(width = matchParent, height = wrapContent)
                orientation = LinearLayout.VERTICAL
                topPadding = dip(16)
                rightPadding = dip(16)
                leftPadding = dip(16)

                spinner{
                    id = R.id.spinner_main
                }
                swipeRefreshLayout {
                    id = R.id.swipemain
                    setColorSchemeResources(colorAccent,
                            android.R.color.holo_green_light,
                            android.R.color.holo_orange_light,
                            android.R.color.holo_red_light)

                    relativeLayout {
                        lparams(width = matchParent, height = wrapContent)

                        recyclerView {
                            id = R.id.list_main
                            lparams(width = matchParent, height = wrapContent)
                            layoutManager = LinearLayoutManager(ctx)
                        }

                        progressBar {
                            id = R.id.progress_main
                        }.lparams { centerHorizontally() }
                    }
                }
            }
        }.view
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<TeamsItem>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }
}
