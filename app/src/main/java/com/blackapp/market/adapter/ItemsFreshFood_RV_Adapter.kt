package com.blackapp.market.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blackapp.market.api.model.M_Items
import com.blackapp.market.databinding.RvItemCannedFoodRowBinding
import com.blackapp.market.databinding.RvItemFreshfoodRowBinding

class ItemsFreshFood_RV_Adapter(
    var itemList: List<M_Items>,
    var listener: OnItemFreshFoodClickListener

) : RecyclerView.Adapter<ItemsFreshFood_RV_Adapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvItemFreshfoodRowBinding.inflate(layoutInflater)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var item: M_Items = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount() = itemList.size

    inner class MyViewHolder(val binding: RvItemFreshfoodRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: M_Items) {
            binding.viewModel = data
            binding.executePendingBindings()
        }

        init {
            binding.root.setOnClickListener {
                var position: Int = adapterPosition
                listener.onItemFreshFoodClick(position)
            }
        }
    }

    interface OnItemFreshFoodClickListener {
        fun onItemFreshFoodClick(position: Int)
    }

}
