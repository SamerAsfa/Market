package com.blackapp.market.db

import androidx.lifecycle.LiveData
import com.blackapp.market.db.model.User

class Repository (private val dao: Dao){

    val readAllData: LiveData<List<User>> = dao.readAllData()

    suspend fun addUser(user: User) {
        dao.addUser(user)
    }


    suspend fun deleteUser(user:User){
        dao.deleteUser(user)
    }

    suspend fun deleteAllUser(){
        dao.deleteAllUser()
    }
}