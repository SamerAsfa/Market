package com.blackapp.market.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.blackapp.market.db.model.User

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Delete
    suspend fun deleteUser(user:User)

    @Query("DELETE  FROM user")
    suspend fun deleteAllUser()

    @Query("SELECT * FROM user ORDER BY id ASC")
    fun readAllData():LiveData<List<User>>

}