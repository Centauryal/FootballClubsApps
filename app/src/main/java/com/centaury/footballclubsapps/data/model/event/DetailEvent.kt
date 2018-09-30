package com.centaury.footballclubsapps.data.model.event

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class DetailEvent(

	@field:SerializedName("events")
	val events: List<EventsItem>
)