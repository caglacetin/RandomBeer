package com.caglacetin.randombeer.common

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageUrl(imageView: ImageView, imageUrl: String?) {
        Glide.with(imageView.context)
          .load(imageUrl)
          .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("visibleIf")
    fun changeVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
}