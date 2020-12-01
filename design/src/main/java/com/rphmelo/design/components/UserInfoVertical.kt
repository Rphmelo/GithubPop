package com.rphmelo.design.components

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.rphmelo.design.R
import com.rphmelo.design.extensions.inflate
import com.rphmelo.design.extensions.loadCircularUrl
import com.rphmelo.design.extensions.visible
import kotlinx.android.synthetic.main.view_user_info_vertical.view.*

class UserInfoVertical @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(R.layout.view_user_info_vertical)
    }

    fun setUserName(userName: String?, contentDescriptionValue: String) {
        setTextViewText(tvUserName, userName, contentDescriptionValue)
    }

    fun setUserFullName(userFullName: String?, contentDescriptionValue: String) {
        setTextViewText(tvUserFullName, userFullName, contentDescriptionValue)
    }

    fun setAvatarUser(imageUrl: String, contentDescriptionValue: String) {
        with(ivAvatarUser) {
            contentDescription = contentDescriptionValue
            visible()
            loadCircularUrl(imageUrl)
        }
    }

    private fun setTextViewText(view: AppCompatTextView, text: String?, contentDescriptionValue: String) {
        text?.let {
            if(it.isNotEmpty()) {
                contentDescription = contentDescriptionValue
                view.text = it
            }
        }
    }
}