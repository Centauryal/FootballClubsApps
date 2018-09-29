package com.centaury.footballclubsapps.data.model.lastschedule

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class LastMatch(

	@field:SerializedName("events")
	val events: List<EventsItem>
)