package com.centaury.footballclubsapps.data.model.pinned

/**
 * Created by Centaury on 09/10/2018.
 */
data class Pinned(val id: Long?, val idEvent: String?, val idHomeTeam: String?, val teamHome: String?, val idAwayTeam: String?,
                  val teamAway: String?, val scoreHome: String?, val scoreAway: String?, val dateEvent: String?) {

    companion object {
        const val TABLE_PINNED: String = "TABLE_PINNED"
        const val ID: String = "ID_"
        const val EVENT_ID = "EVENT_ID"
        const val HOMETEAM_ID = "HOMETEAM_ID"
        const val HOMETEAM = "HOMETEAM"
        const val AWAYTEAM_ID = "AWAYTEAM_ID"
        const val AWAYTEAM = "AWAYTEAM"
        const val HOMESCORE = "HOMESCORE"
        const val AWAYSCORE = "AWAYSCORE"
        const val DATEEVENT = "DATEEVENT"
    }
}