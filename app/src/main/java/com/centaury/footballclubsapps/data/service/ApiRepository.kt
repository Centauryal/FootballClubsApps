package com.centaury.footballclubsapps.data.service

import java.net.URL

/**
 * Created by Centaury on 23/09/2018.
 */
class ApiRepository {

    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}