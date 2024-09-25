package com.appleroid.feature.join.model

import androidx.annotation.StringRes
import androidx.compose.ui.text.input.KeyboardType
import com.appleroid.feature.join.R

enum class InputType(@StringRes val titleRes : Int,@StringRes val btnRes : Int) {
    PHONE_VERIFY(R.string.phone_number, R.string.send),
    VERIFY_NUMBER(R.string.verify_number, R.string.resend),
    NICKNAME(R.string.nickname_label, R.string.duplicate_check)
}