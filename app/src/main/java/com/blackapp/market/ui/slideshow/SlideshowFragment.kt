package com.blackapp.market.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.blackapp.market.R
import com.blackapp.market.api.ApiRepository

class SlideshowFragment : Fragment() {

    private lateinit var slideShowViewModelFactory: SlideshowViewModelFactory
    private lateinit var slideshowViewModel: SlideshowViewModel

    private  lateinit var apiRepository: ApiRepository



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        apiRepository = ApiRepository()
        slideShowViewModelFactory = SlideshowViewModelFactory(apiRepository)
        slideshowViewModel =
            ViewModelProvider(this, slideShowViewModelFactory).get(SlideshowViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)


        return root
    }




}