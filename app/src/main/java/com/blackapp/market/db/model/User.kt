package com.blackapp.market.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class User(
    val username:String,
    val password:String,
    val type:Int,
    val phone:String,
    val email:String,
    val start_subscription:String,
    val end_subscription:String,
    val language:Int
){
    @PrimaryKey(autoGenerate = true)
    var id:Int =0
}

//@ColumnInfo(name = "user_name")
// 1-> Arabic
// 2-> English

