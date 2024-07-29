package com.appleroid.feature.join.model

import androidx.annotation.StringRes
import com.appleroid.feature.join.R

enum class CarrierType(@StringRes val stringRes : Int) {
    KT(R.string.kt),
    SKT(R.string.skt),
    LG(R.string.lg)
}