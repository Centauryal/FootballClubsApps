package com.centaury.footballclubsapps.ui.view.home

import android.database.sqlite.SQLiteConstraintException
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.centaury.footballclubsapps.R
import com.centaury.footballclubsapps.R.color.colorAccent
import com.centaury.footballclubsapps.R.color.colorPrimary
import com.centaury.footballclubsapps.R.menu.detail_menu
import com.centaury.footballclubsapps.data.model.pinned.Pinned
import com.centaury.footballclubsapps.data.model.team.DetailTeamsItem
import com.centaury.footballclubsapps.data.service.ApiRepository
import com.centaury.footballclubsapps.utils.invisible
import com.centaury.footballclubsapps.utils.database
import com.centaury.footballclubsapps.utils.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.delete

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {

    private lateinit var progressBar: ProgressBar
    private lateinit var teamBadge: ImageView
    private lateinit var teamName: TextView
    private lateinit var teamFormedYear: TextView
    private lateinit var teamStadium: TextView
    private lateinit var teamDescription: TextView

    private lateinit var Teampresenter: TeamDetailPresenter
    private lateinit var teams: DetailTeamsItem
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            backgroundColor = Color.WHITE

            scrollView {
                isVerticalScrollBarEnabled = false
                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)
                    linearLayout{
                        lparams(width = matchParent, height = wrapContent)
                        padding = dip(10)
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER_HORIZONTAL

                        teamBadge =  imageView {}.lparams(height = dip(75))

                        teamName = textView{
                            this.gravity = Gravity.CENTER
                            textSize = 20f
                            textColor =ContextCompat.getColor(context, colorAccent)
                        }.lparams{
                            topMargin = dip(5)
                        }

                        teamFormedYear = textView{
                            this.gravity = Gravity.CENTER
                        }

                        teamStadium = textView{
                            this.gravity = Gravity.CENTER
                            textColor = ContextCompat.getColor(context, colorPrimary)
                        }

                        teamDescription = textView().lparams{
                            topMargin = dip(20)
                        }
                    }
                    progressBar = progressBar {
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }
        }

        val intent = intent
        id = intent.getStringExtra("id")

        val request = ApiRepository()
        val gson = Gson()
        Teampresenter = TeamDetailPresenter(this, request, gson)
        Teampresenter.getTeamDetail(id)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamDetail(data: List<DetailTeamsItem>) {
        teams = DetailTeamsItem(data[0].idTeam,
                data[0].strTeam,
                data[0].strTeamBadge)

        Glide.with(ctx).load(data[0].strTeamBadge).into(teamBadge)
        teamName.text = data[0].strTeam
        teamDescription.text = data[0].strDescriptionEN
        teamFormedYear.text = data[0].intFormedYear
        teamStadium.text = data[0].strStadium
    }
}
