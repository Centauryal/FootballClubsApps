package com.centaury.footballclubsapps.ui.view.detail

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.centaury.footballclubsapps.R
import com.centaury.footballclubsapps.data.model.event.EventsItem
import com.centaury.footballclubsapps.data.model.pinned.Pinned
import com.centaury.footballclubsapps.data.model.team.DetailTeamsItem
import com.centaury.footballclubsapps.data.service.ApiRepository
import com.centaury.footballclubsapps.utils.database
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_schedule.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailScheduleActivity : AppCompatActivity(), DetailScheduleView {

    private lateinit var detailPresenter: DetailSchedulePresenter
    private lateinit var events: EventsItem
    private var idEvent: String = ""
    private var idTeamHome: String = ""
    private var idTeamAway: String = ""

    private var menuItem: Menu? = null
    private var isPinned: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_schedule)

        val intent = intent
        idEvent = intent.getStringExtra("detailEvent")

        pinnedState()
        val request = ApiRepository()
        val gson = Gson()

        detailPresenter = DetailSchedulePresenter(this, request, gson)
        detailPresenter.getDetailEvent(idEvent)
    }

    override fun showTeam(data: DetailTeamsItem) {
        Log.d("idHome: ", idTeamHome)
        when (data.idTeam) {
            idTeamHome -> {
                Glide.with(ctx).load(data.strTeamBadge).into(detail_logo_home)
            }
            idTeamAway -> {
                Glide.with(ctx).load(data.strTeamBadge).into(detail_logo_away)
            }
        }
    }

    override fun getDetailEvent(data: EventsItem) {
        events = data/*EventsItem(data.idEvent, data.idHomeTeam, data.idAwayTeam, data.strHomeTeam, data.intHomeScore, data.strAwayTeam,
                data.intAwayScore, data.strLeague, data.dateEvent)*/
        idTeamHome = events.idHomeTeam.toString()
        idTeamAway = events.idAwayTeam.toString()

        when (events.idEvent) {
            idEvent -> {
                detail_name_home.text = events.strHomeTeam
                detail_score_home.text = events.intHomeScore
                detail_name_away.text = events.strAwayTeam
                detail_score_away.text = events.intAwayScore
                detail_league.text = events.strLeague
                detail_date.text = events.dateEvent
            }
        }

        detailPresenter.getTeam(idTeamHome)
        detailPresenter.getTeam(idTeamAway)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setPinned()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_pinned -> {
                if (isPinned) {
                    removePinned()
                } else {
                    addPinned()
                }

                isPinned = !isPinned

                setPinned()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addPinned() {
        try {
            database.use {
                insert(Pinned.TABLE_PINNED,
                        Pinned.EVENT_ID to events.idEvent,
                        Pinned.HOMETEAM_ID to events.idHomeTeam,
                        Pinned.HOMETEAM to events.strHomeTeam,
                        Pinned.AWAYTEAM_ID to events.idAwayTeam,
                        Pinned.AWAYTEAM to events.strAwayTeam,
                        Pinned.HOMESCORE to events.intHomeScore,
                        Pinned.AWAYSCORE to events.intAwayScore,
                        Pinned.DATEEVENT to events.dateEvent)
            }
            toast("Pinned!!")

            Log.d("idEvent:", Pinned.EVENT_ID)
            Log.d("idEventx:", events.idEvent)
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
            Log.d("errorsqlite:", e.localizedMessage)
        }
    }

    private fun removePinned() {
        try {
            database.use {
                delete(Pinned.TABLE_PINNED, "(EVENT_ID = {detailEvent})",
                        "detailEvent" to idEvent)
            }
            toast("Pinned dihapus!!")
        } catch (e: SQLiteConstraintException) {
            toast("Error " + e.localizedMessage)
        }
    }

    private fun setPinned() {
        if (isPinned)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_pin_off)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_pin_on)
    }

    private fun pinnedState() {
        database.use {
            val result = select(Pinned.TABLE_PINNED)
                    .whereArgs("(EVENT_ID = {detailEvent})",
                            "detailEvent" to idEvent)
            val pinned = result.parseList(classParser<Pinned>())
            if (!pinned.isEmpty()) isPinned = true
            setPinned()
        }
    }

}
