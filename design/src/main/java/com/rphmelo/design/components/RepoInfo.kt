package com.rphmelo.design.components

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.rphmelo.design.R
import com.rphmelo.design.extensions.inflate
import kotlinx.android.synthetic.main.view_repo_info.view.*

class RepoInfo @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(R.layout.view_repo_info)
    }

    fun setTitle(userName: String?) {
        setTextViewText(tvTitle, userName)
    }

    fun setDescription(userFullName: String?) {
        setTextViewText(tvDescription, userFullName)
    }

    private fun setTextViewText(view: AppCompatTextView, text: String?) {
        text?.let { if(it.isNotEmpty()) view.text = it }
    }
}