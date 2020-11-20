package com.blackapp.market.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.blackapp.market.R
import com.blackapp.market.api.model.M_Classification
import com.blackapp.market.databinding.RvClassificationRowBinding

class Classification_RV_Adapter(
    var classificationList: List<M_Classification>,
    var listener: OnClassificationClickListener

) : RecyclerView.Adapter<Classification_RV_Adapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvClassificationRowBinding.inflate(layoutInflater)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var item: M_Classification = classificationList[position]
        holder.bind(item)
    }

    override fun getItemCount() = classificationList.size


    inner class MyViewHolder(val binding: RvClassificationRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: M_Classification) {
            binding.viewModel = data
            binding.executePendingBindings()
        }

        init {
            binding.root.setOnClickListener {
                var position: Int = adapterPosition
                listener.onClassificationClick(position)
            }
        }
    }

    interface OnClassificationClickListener {
        fun onClassificationClick(position: Int)
    }

}
