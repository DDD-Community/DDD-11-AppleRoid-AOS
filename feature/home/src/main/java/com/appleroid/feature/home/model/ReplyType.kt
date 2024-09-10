package com.appleroid.feature.home.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.appleroid.feature.home.R

enum class ReplyType(@StringRes val stringRes: Int, @DrawableRes val drawableRes: Int) {
    WRITE(R.string.comment_write, 0),
    SHOW(R.string.comment_show, R.drawable.dropdown_down),
    HIDE(R.string.comment_hide, R.drawable.dropdown_up)
}