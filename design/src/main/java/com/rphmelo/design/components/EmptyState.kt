package com.rphmelo.design.components

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.rphmelo.design.R
import com.rphmelo.design.extensions.inflate
import kotlinx.android.synthetic.main.view_empty_state.view.*

class EmptyState @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    init { inflate(R.layout.view_empty_state) }

    fun setEmptyStateTitle(title: String) {
        tvTitleEmptyState.text = title
    }
    fun setEmptyStateBody(title: String) {
        tvEmptyStateBody.text = title
    }
}