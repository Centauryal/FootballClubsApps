package com.centaury.footballclubsapps.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Centaury on 23/09/2018.
 */
@Parcelize
class Club (val name: String, val image: Int, val desc: String) : Parcelable