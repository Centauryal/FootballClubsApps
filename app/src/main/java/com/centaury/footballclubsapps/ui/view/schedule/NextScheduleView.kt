package com.centaury.footballclubsapps.ui.view.schedule

import com.centaury.footballclubsapps.data.model.nextschedule.NextItem

/**
 * Created by Centaury on 30/09/2018.
 */
interface NextScheduleView {
    fun showNextScheduleList(data: List<NextItem>)
}