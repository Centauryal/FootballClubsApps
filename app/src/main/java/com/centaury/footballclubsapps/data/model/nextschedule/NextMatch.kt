package com.centaury.footballclubsapps.data.model.nextschedule

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class NextMatch(

	@field:SerializedName("events")
	val events: List<NextItem>
)