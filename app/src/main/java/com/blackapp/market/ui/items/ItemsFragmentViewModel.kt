package com.blackapp.market.ui.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackapp.market.api.ApiRepository
import com.blackapp.market.api.model.M_Items
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemsFragmentViewModel(private val apiRepository: ApiRepository): ViewModel() {

    fun getItemsByClassificationWithCallback(language: Int, classification: Int): LiveData<List<M_Items>> {

        var items: MutableLiveData<List<M_Items>> = MutableLiveData()

        viewModelScope.launch {
            apiRepository.getItemsBycClassificationWithCallback(language, classification).enqueue(object :
                Callback<List<M_Items>> {
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