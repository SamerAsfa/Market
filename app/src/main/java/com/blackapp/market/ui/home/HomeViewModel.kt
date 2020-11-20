package com.blackapp.market.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackapp.market.api.ApiRepository
import com.blackapp.market.api.model.M_Classification
import com.blackapp.market.api.model.M_Items
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(private val apiRepository: ApiRepository) : ViewModel() {


    fun getClassification(language: Int): LiveData<List<M_Classification>> {

        var classification: MutableLiveData<List<M_Classification>> =
                MutableLiveData()

        viewModelScope.launch {
            val response = apiRepository.getClassification(language)// language : 1-> Arabic , 2-> English
            classification.value = response
        }
        return classification
    }

    fun getLast10ItemsByClassification(language: Int, classification: Int): LiveData<List<M_Items>> {

        var items: MutableLiveData<List<M_Items>> = MutableLiveData()

        viewModelScope.launch {
            val response = apiRepository.getLast10ItemsBycClassification(language, classification)
            items.value = response
        }
        return items
    }

    fun getClassificationWithCallback(language: Int): LiveData<List<M_Classification>> {
        var classification: MutableLiveData<List<M_Classification>> =
                MutableLiveData()

        viewModelScope.launch {

          apiRepository.getClassificationWithCallback(language).enqueue( object:Callback<List<M_Classification>> {
              override fun onResponse(call: Call<List<M_Classification>>, response: Response<List<M_Classification>>) {
                  if(response.isSuccessful &&response.body() != null){
                      classification.value = response.body()
                  }
              }

              override fun onFailure(call: Call<List<M_Classification>>, t: Throwable) {
              }

          })
        }
        return classification
    }

    fun getLast10ItemsByClassificationWithCallback(language: Int, classification: Int): LiveData<List<M_Items>> {

        var items: MutableLiveData<List<M_Items>> = MutableLiveData()

        viewModelScope.launch {
            apiRepository.getLast10ItemsBycClassificationWithCallback(language, classification).enqueue(object : Callback<List<M_Items>> {
                override fun onResponse(call: Call<List<M_Items>>, response: Response<List<M_Items>>) {
                    if (response.isSuccessful && response.body() != null) {
                        items.value = response.body()
                    }
                }

                override fun onFailure(call: Call<List<M_Items>>, t: Throwable) {
                }
            })
        }
        return items
    }

}
