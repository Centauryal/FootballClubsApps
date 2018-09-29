package com.centaury.footballclubsapps.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.centaury.footballclubsapps.R
import com.centaury.footballclubsapps.data.model.lastschedule.EventsItem
import com.centaury.footballclubsapps.ui.adapter.LastScheduleAdapter.ViewHolder
import kotlinx.android.synthetic.main.item_schedule.view.*

/**
 * Created by Centaury on 29/09/2018.
 */
class LastScheduleAdapter(private val lastMatch: List<EventsItem>): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = lastMatch.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItem(lastMatch[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bindItem(lastMatch: EventsItem) {
            itemView.last_date.text = lastMatch.dateEvent
            itemView.name_club_home.text = lastMatch.strHomeTeam
            itemView.score_club_home.text = lastMatch.intHomeScore
            itemView.name_club_away.text = lastMatch.strAwayTeam
            itemView.score_club_away.text = lastMatch.intAwayScore
        }
    }
}