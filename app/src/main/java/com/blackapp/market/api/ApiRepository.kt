package com.blackapp.market.api

import com.blackapp.market.api.model.M_Classification
import com.blackapp.market.api.model.M_Items
import retrofit2.Call
import retrofit2.Response

class ApiRepository {

    suspend fun getClassification(language:Int):MutableList<M_Classification> {
        return RetrofitInstance.api.getClassification(language)
    }

    suspend fun getClassification1(language:Int):Response<List<M_Classification>> {
        return RetrofitInstance.api.getClassification1(language)
    }
     fun getClassificationWithCallback(language:Int):Call<List<M_Classification>> {
        return RetrofitInstance.api.getClassificationWithCallback(language)
    }

    suspend fun getLast10ItemsBycClassification(language:Int , classification:Int):MutableList<M_Items> {
        return RetrofitInstance.api.getLast10ItemsByClassification(language , classification)
    }

     fun getLast10ItemsBycClassificationWithCallback(language:Int, classification:Int):Call<List<M_Items>>{
        return RetrofitInstance.api.getLast10ItemsByClassificationWithCallback(language , classification )
    }

    fun getItemsBycClassificationWithCallback(language:Int, classification:Int):Call<List<M_Items>>{
        return RetrofitInstance.api.getItemsByClassificationWithCallback(language , classification )
    }

}