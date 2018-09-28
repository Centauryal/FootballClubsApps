package com.centaury.footballclubsapps.ui.view.home

import com.centaury.footballclubsapps.data.model.PremierLeague
import com.centaury.footballclubsapps.data.service.ApiRepository
import com.centaury.footballclubsapps.data.service.TheSportDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Centaury on 23/09/2018.
 */
class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson) {

    fun getTeamList(league: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeams(league)),
                    PremierLeague::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamList(data.teams)
            }
        }
    }
}