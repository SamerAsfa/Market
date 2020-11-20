package com.blackapp.market.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.blackapp.market.db.model.User

@Database(entities = [User::class], version = 1, exportSchema = true)
abstract class MarketDatabase : RoomDatabase() {

    abstract fun dao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: MarketDatabase? = null

        fun getDatabase(context: Context):MarketDatabase{
            val tempInstance = INSTANCE

            if(tempInstance!= null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext ,
                    MarketDatabase::class.java ,
                    "market_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}