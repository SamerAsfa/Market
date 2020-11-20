package com.blackapp.market.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.blackapp.market.R
import com.blackapp.market.api.ApiRepository
import com.blackapp.market.api.model.M_Classification
import kotlinx.android.synthetic.main.fragment.view.*


class Fragment : Fragment()  {

    private var apiRepository: ApiRepository? = null


    private lateinit var mViewModel: FragmentViewMmodel
    private lateinit var mViewModelFactory: FragmentViewMmodelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        apiRepository = ApiRepository()
        mViewModelFactory = FragmentViewMmodelFactory(apiRepository!!)
        mViewModel = ViewModelProvider(this, mViewModelFactory).get(FragmentViewMmodel::class.java)
        val root = inflater.inflate(R.layout.fragment, container, false)

        return root
    }
}