package com.appleroid.feature.join.model

import androidx.annotation.StringRes
import com.appleroid.feature.join.R

enum class PagerType(val index : Int, @StringRes val titleRes : Int, @StringRes val bottomBtnRes : Int) {
    TERM_AGREE(0, R.string.agree_term, R.string.next),
    PHONE_VERIFY(1, R.string.phone_verify, R.string.verify),
    NICKNAME(2, R.string.nickname, R.string.next),
    MBTI(3, R.string.mbti, R.string.complete)
}

