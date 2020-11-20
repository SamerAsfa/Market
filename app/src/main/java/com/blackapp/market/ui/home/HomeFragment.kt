package com.blackapp.market.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.blackapp.market.R
import com.blackapp.market.adapter.*
import com.blackapp.market.api.ApiRepository
import com.blackapp.market.api.model.M_Classification
import com.blackapp.market.api.model.M_Items
import com.blackapp.market.util.MarginItemDecoration
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment(), Classification_RV_Adapter.OnClassificationClickListener,
        ItemsOffers_RV_Adapter.OnItemClickListener,
        ItemsCannedFood_RV_Adapter.OnItemCannedFoodClickListener,
        ItemsFreshFood_RV_Adapter.OnItemFreshFoodClickListener,
        ItemsFreezersFood_RV_Adapter.OnItemFreezersFoodClickListener,
        ItemFruitsFood_RV_Adapter.OnItemFruitsFoodClickListener,
        ItemsVegetablesFood_RV_Adapter.OnItemVegetablesFoodClickListener {

    private lateinit var mViewModel: HomeViewModel
    private lateinit var mViewModelFactory: HomeViewModelFactory
    private lateinit var mApiRepository: ApiRepository

    // list
    private var classificationList: ArrayList<M_Classification> = ArrayList()
    private var itemsOffersList: ArrayList<M_Items> = ArrayList()
    private var itemsCannedFoodList: ArrayList<M_Items> = ArrayList()
    private var itemsFreshFoodList: ArrayList<M_Items> = ArrayList()
    private var itemsFreezersFoodList: ArrayList<M_Items> = ArrayList()
    private var itemsFruitsFoodList: ArrayList<M_Items> = ArrayList()
    private var itemsVegetablesFoodList: ArrayList<M_Items> = ArrayList()

    // adapter
    private var mClassificationAdapter: Classification_RV_Adapter? = null
    private var mItemsOffersOffersAdapter: ItemsOffers_RV_Adapter? = null
    private var mItemCannedFoodAdapter: ItemsCannedFood_RV_Adapter? = null
    private var mItemFreshFoodAdapter: ItemsFreshFood_RV_Adapter? = null
    private var mItemFreezersFoodAdapter: ItemsFreezersFood_RV_Adapter? = null
    private var mItemFruitsFoodAdapter: ItemFruitsFood_RV_Adapter? = null
    private var mItemVegetablesFoodAdapter: ItemsVegetablesFood_RV_Adapter? = null


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        mApiRepository = ApiRepository()
        mViewModelFactory = HomeViewModelFactory(mApiRepository)
        mViewModel = ViewModelProvider(this, mViewModelFactory).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        initAdapter()
        getClassification()
        getItemsOffer()
        getItemsCannedFood()
        getItemFreshFood()
        getItemFreezersFood()
        getItemFruitsFood()
        getItemVegetablesFood()

        initRV(root)

        return root
    }


    private fun initAdapter() {
        mClassificationAdapter = Classification_RV_Adapter(classificationList, this)
        mItemsOffersOffersAdapter = ItemsOffers_RV_Adapter(itemsOffersList, this)
        mItemCannedFoodAdapter = ItemsCannedFood_RV_Adapter(itemsCannedFoodList, this)
        mItemFreshFoodAdapter = ItemsFreshFood_RV_Adapter(itemsFreshFoodList, this)
        mItemFreezersFoodAdapter = ItemsFreezersFood_RV_Adapter(itemsFreezersFoodList, this)
        mItemFruitsFoodAdapter = ItemFruitsFood_RV_Adapter(itemsFruitsFoodList, this)
        mItemVegetablesFoodAdapter = ItemsVegetablesFood_RV_Adapter(itemsVegetablesFoodList, this)
    }

    private fun getClassification() {

        // language : 1-> arabic : 2-> english
        mViewModel.getClassificationWithCallback(2).observe(requireActivity(), Observer { data ->

            if(classificationList.isNotEmpty()){
                classificationList.clear()
            }
            classificationList.addAll(data) // add data to list
            mClassificationAdapter?.notifyDataSetChanged() // refresh adapter
        })
    }

    private fun getItemsOffer() {
        mViewModel.getLast10ItemsByClassificationWithCallback(2, 1)
                .observe(requireActivity(), Observer { data ->

                    if(itemsOffersList.isNotEmpty()){
                        itemsOffersList.clear()
                    }
                    itemsOffersList.addAll(data) // add data ti list
                    mItemsOffersOffersAdapter?.notifyDataSetChanged() // refresh adapter
                })
    }

    private fun getItemsCannedFood() {
        mViewModel.getLast10ItemsByClassificationWithCallback(2, 3)
                .observe(requireActivity(), Observer { data ->

                    if(itemsCannedFoodList.isNotEmpty()){
                        itemsCannedFoodList.clear()
                    }
                    itemsCannedFoodList.addAll(data) // add data ti list
                    mItemCannedFoodAdapter?.notifyDataSetChanged() // refresh adapter
                })
    }

    private fun getItemFreshFood() {
        mViewModel.getLast10ItemsByClassificationWithCallback(2, 5).observe(requireActivity(), Observer { data ->

            if(itemsFreshFoodList.isNotEmpty()){
                itemsFreshFoodList.clear()
            }
            itemsFreshFoodList.addAll(data) // add data ti list
            mItemFreshFoodAdapter?.notifyDataSetChanged() // refresh adapter
        })
    }

    private fun getItemFreezersFood() {
        mViewModel.getLast10ItemsByClassificationWithCallback(2, 4).observe(requireActivity(), Observer { data ->

            if(itemsFreezersFoodList.isNotEmpty()){
                itemsFreezersFoodList.clear()
            }
            itemsFreezersFoodList.addAll(data) // add data ti list
            mItemFreezersFoodAdapter?.notifyDataSetChanged() // refresh adapter
        })
    }

    private fun getItemFruitsFood() {
        mViewModel.getLast10ItemsByClassificationWithCallback(2, 7).observe(requireActivity(), Observer { data ->

            if(itemsFruitsFoodList.isNotEmpty()){
                itemsFruitsFoodList.clear()
            }
            itemsFruitsFoodList.addAll(data) // add data ti list
            mItemFruitsFoodAdapter?.notifyDataSetChanged() // refresh adapter
        })
    }

    private fun getItemVegetablesFood() {
        mViewModel.getLast10ItemsByClassificationWithCallback(2, 6).observe(requireActivity(), Observer { data ->

            if(itemsVegetablesFoodList.isNotEmpty()){
                itemsVegetablesFoodList.clear()
            }
            itemsVegetablesFoodList.addAll(data) // add data ti list
            mItemVegetablesFoodAdapter?.notifyDataSetChanged() // refresh adapter
        })
    }

    private fun initRV(view: View) {

        var itemDecoration: MarginItemDecoration = MarginItemDecoration(7)

        // classification rv
        view.rvHomeFragment_Classification.apply {
            adapter = mClassificationAdapter
            layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }

        // item offer rv
        view.rvHomeFragment_Offers.apply {
            adapter = mItemsOffersOffersAdapter
            layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            addItemDecoration(itemDecoration)
        }

        // rv canned food
        view.rvHomeFragment_CannedFood.apply {
            adapter = mItemCannedFoodAdapter
            layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            addItemDecoration(itemDecoration)
        }

        // rv fresh food
        view.rvHomeFragment_FreshFood.apply {
            adapter = mItemFreshFoodAdapter
            layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            addItemDecoration(itemDecoration)
        }

        // rv freezers food
        view.rvHomeFragment_FreezersFood.apply {
            adapter = mItemFreezersFoodAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            addItemDecoration(itemDecoration)
        }

        // rv fruits food
        view.rvHomeFragment_FruitsFood.apply {
            adapter = mItemFruitsFoodAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            addItemDecoration(itemDecoration)
        }

        // rv vegetables food
        view.rvHomeFragment_VegetablesFood.apply {
            adapter = mItemVegetablesFoodAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            addItemDecoration(itemDecoration)
        }

    }

    override fun onClassificationClick(position: Int) {
        var classification = classificationList[position]
        var action = HomeFragmentDirections.actionNavHomeToItemsFragment(classification)
        findNavController().navigate(action)
    }

    override fun onItemOfferClick(position: Int) {
        var item = itemsOffersList[position]
        var action = HomeFragmentDirections.actionNavHomeToItemDetailsFragment(item)
        findNavController().navigate(action)
    }

    override fun onItemCannedFoodClick(position: Int) {
        var item = itemsCannedFoodList[position]
        var action = HomeFragmentDirections.actionNavHomeToItemDetailsFragment(item)
        findNavController().navigate(action)
    }

    override fun onItemFreshFoodClick(position: Int) {
        var item = itemsFreshFoodList[position]
        var action = HomeFragmentDirections.actionNavHomeToItemDetailsFragment(item)
        findNavController().navigate(action)
    }

    override fun onItemFreezersFoodClick(position: Int) {
        var item = itemsFreezersFoodList[position]
        var action = HomeFragmentDirections.actionNavHomeToItemDetailsFragment(item)
        findNavController().navigate(action)
    }

    override fun onItemFruitsFoodClick(position: Int) {
        var item = itemsFruitsFoodList[position]
        var action = HomeFragmentDirections.actionNavHomeToItemDetailsFragment(item)
        findNavController().navigate(action)
    }

    override fun onItemVegetablesFoodClick(position: Int) {
        var item = itemsVegetablesFoodList[position]
        var action = HomeFragmentDirections.actionNavHomeToItemDetailsFragment(item)
        findNavController().navigate(action)
    }
}