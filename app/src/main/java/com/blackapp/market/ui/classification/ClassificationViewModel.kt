package com.blackapp.market.ui.classification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackapp.market.api.ApiRepository
import com.blackapp.market.api.model.M_Classification
import kotlinx.coroutines.launch

class ClassificationViewModel (private val apiRepository: ApiRepository): ViewModel() {

    fun getClassification(language: Int): LiveData<List<M_Classification>> {

        var classification: MutableLiveData<List<M_Classification>> =
            MutableLiveData()

        viewModelScope.launch {
            val response = apiRepository.getClassification1(language)// language : 1-> Arabic , 2-> English
           if(response.isSuccessful){
               classification.value = response.body()
           }
        }
        return classification
    }
}