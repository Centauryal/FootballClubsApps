package com.centaury.footballclubsapps.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.centaury.footballclubsapps.R
import com.centaury.footballclubsapps.data.model.TeamsItem
import com.centaury.footballclubsapps.ui.adapter.PremierLeagueAdapter.TeamViewHolder
import org.jetbrains.anko.*

/**
 * Created by Centaury on 23/09/2018.
 */
class PremierLeagueAdapter(private val teams: List<TeamsItem>) : RecyclerView.Adapter<TeamViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(teams[position])
    }

    class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val teamBadge: ImageView = view.find(R.id.team_badge)
        private val teamName: TextView = view.find(R.id.team_name)

        fun bindItem(teams: TeamsItem) {
            Glide.with(itemView.context).load(teams.strTeamBadge).into(teamBadge)
            teamName.text = teams.strTeam
        }
    }

    class TeamUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    imageView {
                        id = R.id.team_badge
                    }.lparams{
                        height = dip(50)
                        width = dip(50)
                    }

                    textView {
                        id = R.id.team_name
                        textSize = 16f
                    }.lparams{
                        margin = dip(15)
                    }

                }
            }
        }

    }
}