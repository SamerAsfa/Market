package com.blackapp.market.api

import androidx.lifecycle.LiveData
import com.blackapp.market.api.model.M_Classification
import com.blackapp.market.api.model.M_Items
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("getClassification.php")
    suspend fun getClassification(
        @Query("language") language:Int
    ) : MutableList<M_Classification>

    @GET("getClassification.php")
    suspend fun getClassification1(
        @Query("language") language:Int
    ) : Response<List<M_Classification>>

    @GET("getClassification.php")
     fun getClassificationWithCallback(
            @Query("language") language:Int
    ) : Call<List<M_Classification>>


    @GET("getLast10ItemsByClassification.php")
    suspend fun getLast10ItemsByClassification(
        @Query("language") language:Int ,
        @Query("classification") classification:Int
    ) : MutableList<M_Items>


    @GET("getLast10ItemsByClassification.php")
     fun getLast10ItemsByClassificationWithCallback(
            @Query("language") language:Int ,
            @Query("classification") classification:Int
    ) : Call<List<M_Items>>

    @GET("getItemsByClassification.php")
    fun getItemsByClassificationWithCallback(
        @Query("language") language:Int ,
        @Query("classification") classification:Int
    ) : Call<List<M_Items>>

}