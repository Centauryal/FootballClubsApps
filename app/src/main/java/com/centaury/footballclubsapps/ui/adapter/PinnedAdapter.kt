package com.centaury.footballclubsapps.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.centaury.footballclubsapps.R
import com.centaury.footballclubsapps.R.id.team_badge
import com.centaury.footballclubsapps.R.id.team_name
import com.centaury.footballclubsapps.data.model.pinned.Pinned
import com.centaury.footballclubsapps.ui.adapter.PinnedAdapter.PinnedViewHolder
import kotlinx.android.synthetic.main.item_schedule.view.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by Centaury on 11/10/2018.
 */
class PinnedAdapter(private val pinned: List<Pinned>, private val listener: (Pinned) -> Unit)
    : RecyclerView.Adapter<PinnedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PinnedViewHolder {
        return PinnedViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false))
    }

    override fun getItemCount(): Int = pinned.size

    override fun onBindViewHolder(holder: PinnedViewHolder, position: Int) {
        holder.bindItem(pinned[position], listener)
    }

    class PinnedViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(pinned: Pinned, listener: (Pinned) -> Unit) {
            itemView.name_club_home.text = pinned.teamHome
            itemView.score_club_home.text = pinned.scoreHome
            itemView.name_club_away.text = pinned.teamAway
            itemView.score_club_away.text = pinned.scoreAway
            itemView.last_date.text = pinned.dateEvent

            itemView.onClick {
                listener(pinned)
            }
        }
    }

}