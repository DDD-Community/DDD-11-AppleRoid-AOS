package com.appleroid.feature.join.model

import androidx.annotation.StringRes
import com.appleroid.feature.join.R

enum class CarrierType(@StringRes val stringRes : Int) {
    SKT(R.string.skt),
    KT(R.string.kt),
    LG(R.string.lg),
    SKT_BUDGET(R.string.skt_budget),
    KT_BUDGET(R.string.kt_budget),
    LG_BUDGET(R.string.lg_budget)
}