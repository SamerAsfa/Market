package com.blackapp.market.ui.items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.blackapp.market.R
import com.blackapp.market.ui.MainActivity
import com.bumptech.glide.Glide
import com.travijuu.numberpicker.library.Enums.ActionEnum
import com.travijuu.numberpicker.library.Interface.ValueChangedListener
import kotlinx.android.synthetic.main.fragment_item_details.view.*

class ItemDetailsFragment : Fragment() {

    private val args by navArgs<ItemDetailsFragmentArgs>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // change title bar name as item name
        (activity as MainActivity).supportActionBar?.title = args.Item.name.toString()
        var view = inflater.inflate(R.layout.fragment_item_details, container, false)

        fillUiData(view)
        uiListener(view)
        return view
    }

    private fun uiListener(view: View) {
        view.numberPickerItemDetailsFragment_ItemCount.setValueChangedListener(object : ValueChangedListener {
            override fun valueChanged(value: Int, action: ActionEnum?) {

                var price: String = args.Item.price // item price
                var total: Double = value * (price.toDouble()) // total = item price * number of item
                total = Math.round(total * 100.0) / 100.0  // round to 3 digit
                view.txtItemDetailsFragment_TotalAmount.text = total.toString() // srt total amount to text view
            }
        })
    }

    private fun fillUiData(view: View) {

        var item = args.Item
        var itemCount = view.numberPickerItemDetailsFragment_ItemCount.value
        var totalAmount = itemCount * (item.price.toDouble())

        view.txtItemDetailsFragment_Name.text = item.name
        view.txtItemDetailsFragmentDetails.text = item.details
        view.txtItemDetailsFragmentPrice.text = item.price
        view.txtItemDetailsFragmentOldPrice.text = item.old_price
        view.txtItemDetailsFragment_TotalAmount.text = totalAmount.toString()

        Glide.with(view.context)
                .load(item.image)
                .into(view.imgItemDetailsFragment)

    }

}