package com.blackapp.market.api.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

//@Entity(tableName = "user_table")
@Parcelize
data class M_Classification(
        @SerializedName("id")
        var id: Int,
        @SerializedName("name")
        var name: String,
        @SerializedName("image")
        var image: String,
):Parcelable
