package com.rphmelo.design.components

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.rphmelo.design.R
import com.rphmelo.design.extensions.inflate
import com.rphmelo.design.extensions.visible
import kotlinx.android.synthetic.main.view_footer_info.view.*

class FooterInfo @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(R.layout.view_footer_info)
    }

    fun setCount(count: String?) {
        tvCount.text = count
    }

    fun setImageView(@DrawableRes imageRes: Int?) {
        imageRes?.let {
            with(ivImage) {
                visible()
                setImageResource(it)
            }
        }
    }
}