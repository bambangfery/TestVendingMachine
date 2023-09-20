package com.bambang.vendingmachine.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Snack(
    val idSnack: Int? = null,
    val titleSnack: String? = null,
    val imgSnack: String? = null,
    val priceSnack: Int? = null,
    val stockSnack: Int? = null,
):Parcelable