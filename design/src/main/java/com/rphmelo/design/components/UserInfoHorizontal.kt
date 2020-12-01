package com.rphmelo.design.components

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.rphmelo.design.R
import com.rphmelo.design.extensions.inflate
import com.rphmelo.design.extensions.loadCircularUrl
import com.rphmelo.design.extensions.visible
import kotlinx.android.synthetic.main.view_user_info_horizontal.view.*

class UserInfoHorizontal @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(R.layout.view_user_info_horizontal)
    }

    fun setUserName(userName: String?) {
        setTextViewText(tvUserName, userName)
    }

    fun setUserFullName(userFullName: String?) {
        setTextViewText(tvUserFullName, userFullName)
    }

    fun setAvatarUser(imageUrl: String, contentDescriptionValue: String) {
        with(ivAvatarUser) {
            contentDescription = contentDescriptionValue
            visible()
            loadCircularUrl(imageUrl)
        }
    }

    private fun setTextViewText(view: AppCompatTextView, text: String?) {
        text?.let { if(it.isNotEmpty()) view.text = it }
    }
}