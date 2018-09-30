package com.centaury.footballclubsapps.data.model.event

import com.google.gson.annotations.SerializedName

data class DetailEventsItem(

	@SerializedName("idEvent")
	val idEvent: String? = null,

	@SerializedName("idHomeTeam")
	val idHomeTeam: String? = null,

	@SerializedName("intHomeScore")
	val intHomeScore: String? = null,

	@SerializedName("dateEvent")
	val dateEvent: String? = null,

	@SerializedName("strAwayTeam")
	val strAwayTeam: String? = null,

	@SerializedName("idAwayTeam")
	val idAwayTeam: String? = null,

	@SerializedName("strHomeTeam")
	val strHomeTeam: String? = null,

	@SerializedName("strLeague")
	val strLeague: String? = null,

	@SerializedName("intAwayScore")
	val intAwayScore: String? = null
)