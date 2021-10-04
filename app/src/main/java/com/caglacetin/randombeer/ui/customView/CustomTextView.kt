package com.caglacetin.randombeer.ui.customView

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.caglacetin.randombeer.R
import com.caglacetin.randombeer.R.string
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
      listenTextSize()
    }

  private var color: String? = ""
    set(value) {
      field = value
      listenTextColor()
    }

  init {
    inflate(getContext(), R.layout.layout_custom_textview, this)
    taglineTextView = findViewById(R.id.tv_beer_tagline)
    sizeSlider = findViewById(R.id.slider_tagline_size)
    colorEditText = findViewById(R.id.et_tagline_color)

    val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView)
    title = typedArray.getString(R.styleable.CustomTextView_textTitle)
    setTextTitle(title.orEmpty())
    color = typedArray.getString(R.styleable.CustomTextView_textColor)
    typedArray.recycle()
  }

  private fun listenTextColor() {
    colorEditText.setOnEditorActionListener(object : OnEditorActionListener {
      override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
          try {
            taglineTextView.setTextColor(Color.parseColor(colorEditText.text.toString()))
          } catch (exception: Exception) {
            Toast.makeText(
              context,
              context.getString(string.color_code_is_not_valid),
              Toast.LENGTH_SHORT)
              .show()
          }
          return true
        }
        return false
      }
    })
  }

  private fun listenTextSize() {
    sizeSlider.addOnChangeListener { slider, value, fromUser ->
      taglineTextView.textSize = value
    }
  }

  fun setTextTitle(title: String) {
    taglineTextView.text = title
  }

}
