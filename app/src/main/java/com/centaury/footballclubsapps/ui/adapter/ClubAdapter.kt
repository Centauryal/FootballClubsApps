package com.centaury.footballclubsapps.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.centaury.footballclubsapps.R
import com.centaury.footballclubsapps.data.model.Club
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_club.*

/**
 * Created by Centaury on 23/09/2018.
 */
class ClubAdapter (private val context: Context, private val clubs: List<Club>, private val listener: (Club) -> Unit)
    : RecyclerView.Adapter<ClubAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.view_club, parent, false))

    override fun getItemCount(): Int = clubs.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(clubs[position], listener)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindItem(clubs: Club, listener: (Club) -> Unit){
            name_club.text = clubs.name
            Glide.with(containerView).load(clubs.image).into(image_club)
            containerView.setOnClickListener { listener(clubs) }
        }
    }
}