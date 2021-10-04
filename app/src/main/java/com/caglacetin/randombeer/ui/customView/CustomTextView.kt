package com.caglacetin.randombeer.ui.customView

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.caglacetin.randombeer.R
import com.google.android.material.slider.Slider

class CustomTextView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

  private var taglineTextView: TextView
  private var sizeSlider: Slider
  private var colorEditText: EditText

  private var title: String? = ""
    set(value) {
      field = value
      listenEdittext()
    }

  init {
    inflate(getContext(), R.layout.layout_custom_textview, this)
    taglineTextView = findViewById(R.id.tv_beer_tagline2)
    sizeSlider = findViewById(R.id.slider_tagline_size2)
    colorEditText = findViewById(R.id.et_tagline_color2)

    val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView)
    title = typedArray.getString(R.styleable.CustomTextView_textTitle)
    setTextTitle(title.orEmpty())
    typedArray.recycle()

  }

  private fun listenEdittext() {
    sizeSlider.addOnChangeListener { slider, value, fromUser ->
      taglineTextView.textSize = value
    }
  }

  fun setTextTitle(title: String) {
    taglineTextView.text = title
  }

}
