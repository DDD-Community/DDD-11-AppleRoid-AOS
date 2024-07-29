package com.appleroid.feature.join.model

import androidx.annotation.StringRes
import com.appleroid.feature.join.R

enum class TermType(@StringRes val stringRes : Int) {
    ALL_TERM(R.string.all_term_agree),
    PRIVACY_TERM(R.string.privacy_agree),
    SERVICE_TERM(R.string.service_agree)
}