package com.centaury.footballclubsapps.ui.view.home

import com.centaury.footballclubsapps.data.model.team.Team
import com.centaury.footballclubsapps.data.service.ApiRepository
import com.centaury.footballclubsapps.data.service.TheSportDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Centaury on 09/10/2018.
 */
class TeamDetailPresenter(private val view: TeamDetailView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson) {

    fun getTeamDetail(teamId: String) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getDetailTeam(teamId)),
                    Team::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamDetail(data.teams)
            }
        }
    }
}