package com.caglacetin.randombeer.ui.customView

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.caglacetin.randombeer.R
import com.caglacetin.randombeer.R.layout
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.slider.Slider

class RadiusImageView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

  private var radiusSlider: Slider
  private var heightSlider: Slider
  private var widthSlider: Slider
  private var beerImage: ShapeableImageView
  private var errorMessage: TextView

  private var imageRadius: Float = 0f
    set(value) {
      field = value
      listenRadiusSlider()
    }

  private var imageHeight: Int = 0
    set(value) {
      field = value
      listenHeightSlider()
    }

  private var imageWidth: Int = 0
    set(value) {
      field = value
      listenWidthSlider()
    }

  init {
    inflate(getContext(), layout.layout_custom_imageview, this)
    radiusSlider = findViewById(R.id.slider_image_radius)
    heightSlider = findViewById(R.id.slider_image_height)
    widthSlider = findViewById(R.id.slider_image_width)
    beerImage = findViewById(R.id.siv_beer_image)
    errorMessage = findViewById(R.id.tv_error_message)

    val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RadiusImageView)
    imageRadius = typedArray.getFloat(R.styleable.RadiusImageView_radius, 0f)
    imageHeight = beerImage.height
    imageWidth = beerImage.width

    typedArray.recycle()

  }

  private fun listenHeightSlider() {
    heightSlider.addOnChangeListener { slider, value, fromUser ->
      beerImage.layoutParams.height = value.toInt()
      beerImage.requestLayout()
    }
  }

  private fun listenWidthSlider() {
    widthSlider.addOnChangeListener { slider, value, fromUser ->
      beerImage.layoutParams.width = value.toInt()
      beerImage.requestLayout()
    }
  }


  private fun listenRadiusSlider() {
    radiusSlider.addOnChangeListener { slider, value, fromUser ->
      val shape = beerImage.shapeAppearanceModel.toBuilder()
        .setAllCornerSizes(value)
        .build()

      beerImage.shapeAppearanceModel = shape
    }
  }

  fun setRadiusToImage(url: String?) {
    if (url.isNullOrEmpty()) {
      radiusSlider.visibility = GONE
      heightSlider.visibility = GONE
      widthSlider.visibility = GONE
      beerImage.visibility = GONE
      errorMessage.visibility = visibility
    } else {
      Glide.with(context)
        .load(url)
        .into(beerImage)
    }
  }
}
