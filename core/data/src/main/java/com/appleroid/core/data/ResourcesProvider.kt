package com.appleroid.core.data

import android.content.Context
import android.util.TypedValue
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

class ResourcesProvider(private val context: Context) {
    fun getString(@StringRes stringResId: Int): String = context.getString(stringResId)
    fun getString(@StringRes stringResId: Int, vararg formatArgs: Any?): String = context.getString(stringResId, formatArgs)
    fun getDeviceHeight(): Int = context.resources.displayMetrics.heightPixels
    fun getDeviceWidth(): Int = context.resources.displayMetrics.widthPixels
    fun getDrawableId(@DrawableRes drawableRes: Int): Int = drawableRes
}