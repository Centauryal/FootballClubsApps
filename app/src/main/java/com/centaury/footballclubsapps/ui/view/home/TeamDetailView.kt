package com.centaury.footballclubsapps.ui.view.home

import com.centaury.footballclubsapps.data.model.league.TeamsItem
import com.centaury.footballclubsapps.data.model.team.DetailTeamsItem
import com.centaury.footballclubsapps.data.model.team.Team

/**
 * Created by Centaury on 09/10/2018.
 */
interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<DetailTeamsItem>)
}