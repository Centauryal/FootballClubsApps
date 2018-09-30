package com.centaury.footballclubsapps.ui.view.detail

import com.centaury.footballclubsapps.data.model.event.DetailEventsItem
import com.centaury.footballclubsapps.data.model.team.DetailTeamsItem

/**
 * Created by Centaury on 30/09/2018.
 */
interface DetailScheduleView {
    fun showTeam(data: DetailTeamsItem)
    fun showDetailEvent(data: DetailEventsItem)
}