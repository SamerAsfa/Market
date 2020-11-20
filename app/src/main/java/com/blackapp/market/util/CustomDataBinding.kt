package com.blackapp.market.util


import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun bindLoadImage(imageView: ImageView, imageUrl: String) {
    Glide.with(imageView.context)
        .load(imageUrl)
        .into(imageView)
}

@BindingAdapter("rvItemStockOut")
fun bindRVItemStockOut(constraintLayout: ConstraintLayout, stockOut: Int) {
    if (stockOut == 1) {
        constraintLayout.visibility = View.VISIBLE
    } else {
        constraintLayout.visibility = View.GONE
    }
}


