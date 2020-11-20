package com.blackapp.market.ui.classification

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.blackapp.market.R
import com.blackapp.market.adapter.MenuClassification_RV_Adapter
import com.blackapp.market.api.ApiRepository
import com.blackapp.market.api.model.M_Classification
import com.blackapp.market.util.MarginItemDecoration
import kotlinx.android.synthetic.main.fragment_classification.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*
import kotlin.collections.ArrayList

class ClassificationFragment : Fragment(),
    MenuClassification_RV_Adapter.OnClassificationClickListener {

    private lateinit var mViewModel: ClassificationViewModel
    private lateinit var mViewModelFactory: ClassificationViewModelFactory
    private lateinit var apiRepository: ApiRepository

    // adapter
    private var mAdapter: MenuClassification_RV_Adapter? = null

    // list
    private var mClassificationList: ArrayList<M_Classification> = ArrayList()
    private var mClassificationDisplayList: ArrayList<M_Classification> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true); // display  search menu
        apiRepository = ApiRepository()
        mViewModelFactory = ClassificationViewModelFactory(apiRepository)
        mViewModel =
            ViewModelProvider(this, mViewModelFactory).get(ClassificationViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_classification, container, false)


        initAdapter()
        getClassification()
        initRV(root)
        return root
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

                        mClassificationDisplayList.clear()
                        val search = newText.toLowerCase(Locale.getDefault())

                        mClassificationList.forEach {

                            if (it.name.toLowerCase(Locale.getDefault()).contains(search)) {
                                mClassificationDisplayList.add(it)
                            }
                            mAdapter?.notifyDataSetChanged()
                        }

                    } else {
                        mClassificationDisplayList.clear()
                        mClassificationDisplayList.addAll(mClassificationList)
                        mAdapter?.notifyDataSetChanged()
                    }
                    return true
                }

            })
        }
        super.onCreateOptionsMenu(menu, inflater)

    }

    private fun initAdapter() {
        mAdapter = MenuClassification_RV_Adapter(mClassificationDisplayList, this)
    }

    private fun getClassification() {
        mViewModel.getClassification(2).observe(requireActivity(), Observer { data ->
            if (mClassificationDisplayList.isNotEmpty()) {
                mClassificationDisplayList.clear()
                mClassificationList.clear()
            }

            mClassificationDisplayList.addAll(data)
            mClassificationList.addAll(data)

            // refresh adapter
            mAdapter?.notifyDataSetChanged()
        })
    }

    private fun initRV(view: View) {

        var itemDecoration: MarginItemDecoration = MarginItemDecoration(7)

        view.rvClassificationFragment.apply {
            adapter = mAdapter
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            addItemDecoration(itemDecoration)
        }
    }

    override fun onClassificationClick(position: Int) {

        var classification = mClassificationDisplayList[position]
        var action =
            ClassificationFragmentDirections.actionNavClassificationToItemsFragment(classification)
        findNavController().navigate(action)
    }
}