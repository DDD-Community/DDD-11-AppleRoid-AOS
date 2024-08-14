package com.appleroid.feature.join.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.appleroid.feature.join.R


const val ENERGY_DIRECTION = 0
const val RECOGNIZE = 1
const val JUDGMENT = 2
const val PLANNING = 3

enum class MbtiType(
    val key : String,
    val type : Int,
    @StringRes val stringRes: Int,
    @DrawableRes val drawableRes: Int
){
    I("I",ENERGY_DIRECTION,R.string.mbti_i, R.drawable.icon_i),
    E("E",ENERGY_DIRECTION,R.string.mbti_e, R.drawable.icon_e),
    S("S",RECOGNIZE,R.string.mbti_s, R.drawable.icon_s),
    N("N",RECOGNIZE,R.string.mbti_n, R.drawable.icon_n),
    T("T",JUDGMENT,R.string.mbti_t, R.drawable.icon_t),
    F("F",JUDGMENT,R.string.mbti_f, R.drawable.icon_f),
    P("P",PLANNING,R.string.mbti_p, R.drawable.icon_p),
    J("J",PLANNING,R.string.mbti_j, R.drawable.icon_j)
}

enum class MbtiImage(
    val value : String,
    @DrawableRes val drawableRes: Int
){
    ENFJ("ENFJ",R.drawable.img_enfj),
    ENFP("ENFP",R.drawable.img_enfp),
    ENTJ("ENTJ",R.drawable.img_entj),
    ENTP("ENTP",R.drawable.img_entp),
    ESFJ("ESFJ",R.drawable.img_esfj),
    ESFP("ESFP",R.drawable.img_esfp),
    ESTJ("ESTJ",R.drawable.img_estj),
    ESTP("ESTP",R.drawable.img_estp),
    INFJ("INFJ",R.drawable.img_infj),
    INFP("INFP",R.drawable.img_infp),
    INTJ("INTJ",R.drawable.img_intj),
    INTP("INTP",R.drawable.img_intp),
    ISFJ("ISFJ",R.drawable.img_isfj),
    ISFP("ISFP",R.drawable.img_isfp),
    ISTJ("ISTJ",R.drawable.img_istj),
    ISTP("ISTP",R.drawable.img_istp)
}