package com.blackapp.market.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blackapp.market.api.model.M_Items
import com.blackapp.market.databinding.RvItemCannedFoodRowBinding

class ItemsCannedFood_RV_Adapter(
    var itemList: List<M_Items>,
    var listenerCannedFood: OnItemCannedFoodClickListener

) : RecyclerView.Adapter<ItemsCannedFood_RV_Adapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvItemCannedFoodRowBinding.inflate(layoutInflater)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var item: M_Items = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount() = itemList.size

    inner class MyViewHolder(val binding: RvItemCannedFoodRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: M_Items) {
            binding.viewModel = data
            binding.executePendingBindings()
        }

        init {
            binding.root.setOnClickListener {
                var position: Int = adapterPosition
                listenerCannedFood.onItemCannedFoodClick(position)
            }
        }
    }

    interface OnItemCannedFoodClickListener {
        fun onItemCannedFoodClick(position: Int)
    }

}
