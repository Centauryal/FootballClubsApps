package com.centaury.footballclubsapps.ui.view.home

import com.centaury.footballclubsapps.data.model.league.TeamsItem

/**
 * Created by Centaury on 23/09/2018.
 */
interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<TeamsItem>)
}