package com.centaury.footballclubsapps.ui.view.detail

import com.centaury.footballclubsapps.data.model.event.DetailEvent
import com.centaury.footballclubsapps.data.model.team.Team
import com.centaury.footballclubsapps.data.service.ApiRepository
import com.centaury.footballclubsapps.data.service.TheSportDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Centaury on 30/09/2018.
 */
class DetailSchedulePresenter(private val view: DetailScheduleView,
                              private val apiRepository: ApiRepository,
                              private val gson: Gson){

    fun getTeam(idTeam: String){
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getDetailTeam(idTeam)),
                    Team::class.java
            )

            uiThread {
                view.showTeam(data.teams.first())
            }
        }
    }

    fun getDetailEvent(idEvent: String){
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getDetailTeam(idEvent)),
                    DetailEvent::class.java
            )

            uiThread {
                view.showDetailEvent(data.events.first())
            }
        }
    }
}