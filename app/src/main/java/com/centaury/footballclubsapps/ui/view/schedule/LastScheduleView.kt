package com.centaury.footballclubsapps.ui.view.schedule

import com.centaury.footballclubsapps.data.model.lastschedule.EventsItem
import com.centaury.footballclubsapps.data.model.nextschedule.NextItem

/**
 * Created by Centaury on 29/09/2018.
 */
interface LastScheduleView {
    fun showTeamList(data: List<EventsItem>)
}