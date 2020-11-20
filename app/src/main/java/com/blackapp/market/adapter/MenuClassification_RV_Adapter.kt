package com.blackapp.market.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blackapp.market.api.model.M_Classification
import com.blackapp.market.databinding.ClassificationRvRowBinding


class MenuClassification_RV_Adapter(
    var classificationList: List<M_Classification>,
    var listener: OnClassificationClickListener

) : RecyclerView.Adapter<MenuClassification_RV_Adapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ClassificationRvRowBinding.inflate(layoutInflater)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var item: M_Classification = classificationList[position]
        holder.bind(item)
    }

    override fun getItemCount() = classificationList.size


    inner class MyViewHolder(val binding: ClassificationRvRowBinding) :
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
