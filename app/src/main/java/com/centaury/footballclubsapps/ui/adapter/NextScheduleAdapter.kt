package com.centaury.footballclubsapps.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.centaury.footballclubsapps.R
import com.centaury.footballclubsapps.data.model.nextschedule.NextItem
import com.centaury.footballclubsapps.ui.adapter.NextScheduleAdapter.ViewHolder
import kotlinx.android.synthetic.main.item_schedule.view.*

/**
 * Created by Centaury on 30/09/2018.
 */
class NextScheduleAdapter(private val nextMatch: List<NextItem>): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = nextMatch.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(nextMatch[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bindItem(nextMatch: NextItem) {
            itemView.last_date.text = nextMatch.dateEvent
            itemView.name_club_home.text = nextMatch.strHomeTeam
            itemView.score_club_home.text = nextMatch.intHomeScore.toString()
            itemView.name_club_away.text = nextMatch.strAwayTeam
            itemView.score_club_away.text = nextMatch.intAwayScore.toString()
        }
    }
}