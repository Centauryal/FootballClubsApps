package com.centaury.footballclubsapps.ui.view.schedule

import com.centaury.footballclubsapps.data.model.nextschedule.NextMatch
import com.centaury.footballclubsapps.data.service.ApiRepository
import com.centaury.footballclubsapps.data.service.TheSportDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Centaury on 30/09/2018.
 */
class NextSchedulePresenter(private val view: NextScheduleView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson) {

    fun getNextMatchList() {
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getNextMatch()),
                    NextMatch::class.java
            )

            uiThread {
                view.showNextScheduleList(data.events)
            }
        }
    }
}