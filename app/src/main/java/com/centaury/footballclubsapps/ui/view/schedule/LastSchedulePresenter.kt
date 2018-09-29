package com.centaury.footballclubsapps.ui.view.schedule

import com.centaury.footballclubsapps.data.model.lastschedule.LastMatch
import com.centaury.footballclubsapps.data.service.ApiRepository
import com.centaury.footballclubsapps.data.service.TheSportDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Centaury on 29/09/2018.
 */
class LastSchedulePresenter(private val viewLast: LastScheduleView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson) {

    fun getLastMatchList() {
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getLastMatch()),
                    LastMatch::class.java
            )

            uiThread {
                viewLast.showTeamList(data.events)
            }
        }
    }
}