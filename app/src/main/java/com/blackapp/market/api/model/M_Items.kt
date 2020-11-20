package com.blackapp.market.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class M_Items(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("details")
    var details: String,
    @SerializedName("image")
    var image: String,
    @SerializedName("price")
    var price: String,
    @SerializedName("old_price")
    var old_price: String,
    @SerializedName("out_of_sale")
    var out_of_sale: Int,
): Parcelable
