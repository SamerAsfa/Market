package com.blackapp.market.ui.items

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.blackapp.market.R
import com.blackapp.market.adapter.ItemsFragment_RV_Adapter
import com.blackapp.market.api.ApiRepository
import com.blackapp.market.api.model.M_Items
import com.blackapp.market.ui.MainActivity
import com.blackapp.market.ui.home.HomeFragmentDirections
import com.blackapp.market.util.MarginItemDecoration
import kotlinx.android.synthetic.main.fragment_items.*
import kotlinx.android.synthetic.main.fragment_items.view.*
import java.util.*
import kotlin.collections.ArrayList

class ItemsFragment : Fragment(), ItemsFragment_RV_Adapter.OnItemClickListener {

    private val args by navArgs<ItemsFragmentArgs>()

    private lateinit var mViewModel: ItemsFragmentViewModel
    private lateinit var mViewModelFactory: ItemsFragmentViewModelFactory
    private lateinit var mApiRepository: ApiRepository

    // list
    private var itemList: ArrayList<M_Items> = ArrayList()
    private var itemDisplayList: ArrayList<M_Items> = ArrayList()

    // adapter
    private var mAdapter: ItemsFragment_RV_Adapter? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // change title bar name as classification name
        (activity as MainActivity).supportActionBar?.title = args.Classification.name.toString()
        setHasOptionsMenu(true); // display  search menu

        mApiRepository = ApiRepository()
        mViewModelFactory = ItemsFragmentViewModelFactory(mApiRepository)
        mViewModel =
                ViewModelProvider(this, mViewModelFactory).get(ItemsFragmentViewModel::class.java)
        var view = inflater.inflate(R.layout.fragment_items, container, false)


        initAdapter()
        getItemByClassification()
        initRV(view)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val menuItem = menu.findItem(R.id.menu_item_search)

        if (menuItem != null) {
            val searchView = menuItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    if (newText!!.isNotEmpty()) {

                        itemDisplayList.clear()
                        val search = newText.toLowerCase(Locale.getDefault())

                        itemList.forEach {

                            if (it.name.toLowerCase(Locale.getDefault()).contains(search) ||
                                    it.details.toLowerCase(Locale.getDefault()).contains(search) ||
                                    it.price.toLowerCase(Locale.getDefault()).contains(search)) {

                                itemDisplayList.add(it)
                            }
                            mAdapter?.notifyDataSetChanged()
                        }

                    } else {
                        itemDisplayList.clear()
                        itemDisplayList.addAll(itemList)
                        mAdapter?.notifyDataSetChanged()
                    }
                    return true
                }

            })
        }
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)

    }

    private fun initAdapter() {
        mAdapter = ItemsFragment_RV_Adapter(itemDisplayList, this)
    }

    private fun getItemByClassification() {

        var classificationId = args.Classification.id
        mViewModel.getItemsByClassificationWithCallback(2, classificationId)
                .observe(requireActivity(), Observer { data ->

                    if (itemList.isNotEmpty()) {
                        itemList.clear()
                        itemDisplayList.clear()
                    }
                    if (data.isEmpty()) {
                        // check if data is empty then show nothing here
                        // else show data to recyclerview
                        view?.linItemsFragment_nothingHere?.visibility = View.VISIBLE
                    } else {
                        view?.linItemsFragment_nothingHere?.visibility = View.GONE
                        itemList.addAll(data) // add data ti list
                        itemDisplayList.addAll(data)
                        mAdapter?.notifyDataSetChanged() // refresh adapter
                    }
                })
    }

    private fun initRV(view: View?) {

        var itemDecoration: MarginItemDecoration = MarginItemDecoration(7)

        view?.rvItemsFragment?.apply {

            adapter = mAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            // GridLayoutManager(requireContext() ,3)//GridLayoutManager(requireContext(),3))
            setHasFixedSize(true)
            addItemDecoration(itemDecoration)
        }
    }

    override fun onItemOfferClick(position: Int) {
        var item = itemList[position]
        var action = ItemsFragmentDirections.actionItemsFragmentToItemDetailsFragment(item)
        findNavController().navigate(action)
    }
}