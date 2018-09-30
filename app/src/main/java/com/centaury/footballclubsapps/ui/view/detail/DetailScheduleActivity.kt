package com.centaury.footballclubsapps.ui.view.detail

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.centaury.footballclubsapps.R
import com.centaury.footballclubsapps.data.model.event.DetailEventsItem
import com.centaury.footballclubsapps.data.model.team.DetailTeamsItem
import com.centaury.footballclubsapps.data.service.ApiRepository
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_schedule.*
import kotlinx.android.synthetic.main.item_schedule.*
import org.jetbrains.anko.act
import org.jetbrains.anko.ctx

class DetailScheduleActivity : AppCompatActivity(), DetailScheduleView {

    private lateinit var detailPresenter: DetailSchedulePresenter
    private var idEvent: String = ""
    private var idTeamHome: String = ""
    private var idTeamAway: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_schedule)

        val intent = intent
        idEvent = intent.getStringExtra("detailEvent")

        val request = ApiRepository()
        val gson = Gson()

        detailPresenter = DetailSchedulePresenter(this, request, gson)
        detailPresenter.getDetailEvent(idEvent)
    }

    override fun showTeam(data: DetailTeamsItem) {
        when(data.idTeam){
            idTeamHome -> {
                Glide.with(ctx).load(data.strTeamBadge).into(detail_logo_home)
            }
            idTeamAway -> {
                Glide.with(ctx).load(data.strTeamBadge).into(detail_logo_away)
            }
        }
    }

    override fun showDetailEvent(data: DetailEventsItem) {
        idTeamHome = data.idHomeTeam.toString()
        idTeamAway = data.idAwayTeam.toString()

        when(data.idEvent){
            idEvent -> {
                name_club_home.text = data.strHomeTeam
                score_club_home.text = data.intHomeScore
                name_club_away.text = data.strHomeTeam
                score_club_away.text = data.intAwayScore
                detail_league.text = data.strLeague
                detail_date.text = data.dateEvent
            }
        }

        detailPresenter.getTeam(idTeamHome)
        detailPresenter.getTeam(idTeamAway)
    }
}
