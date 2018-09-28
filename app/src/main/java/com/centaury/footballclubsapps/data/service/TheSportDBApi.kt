package com.centaury.footballclubsapps.data.service

import android.net.Uri
import com.centaury.footballclubsapps.BuildConfig

/**
 * Created by Centaury on 23/09/2018.
 */
object TheSportDBApi {
    fun getTeams(league: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("search_all_teams.php")
                .appendQueryParameter("l", league)
                .build()
                .toString()
    }
}