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
    @StringRes val stringRes: Int
){
    I("I",ENERGY_DIRECTION,R.string.mbti_i),
    E("E",ENERGY_DIRECTION,R.string.mbti_e),
    S("S",RECOGNIZE,R.string.mbti_s),
    N("N",RECOGNIZE,R.string.mbti_n),
    T("T",JUDGMENT,R.string.mbti_t),
    F("F",JUDGMENT,R.string.mbti_f),
    P("P",PLANNING,R.string.mbti_p),
    J("J",PLANNING,R.string.mbti_j)
}

enum class MbtiResult(
    val value : String,
    @DrawableRes val drawableRes: Int,
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int
){
    ENFJ("ENFJ",R.drawable.img_login_enfj, R.string.enfj_title, R.string.enfj_discription),
    ENFP("ENFP",R.drawable.img_login_enfp, R.string.enfp_title, R.string.enfp_discription),
    ENTJ("ENTJ",R.drawable.img_login_entj, R.string.entj_title, R.string.entj_discription),
    ENTP("ENTP",R.drawable.img_login_entp, R.string.entp_title, R.string.entp_discription),
    ESFJ("ESFJ",R.drawable.img_login_esfj, R.string.esfj_title, R.string.esfj_discription),
    ESFP("ESFP",R.drawable.img_login_esfp, R.string.esfp_title, R.string.esfp_discription),
    ESTJ("ESTJ",R.drawable.img_login_estj, R.string.estj_title, R.string.estj_discription),
    ESTP("ESTP",R.drawable.img_login_estp, R.string.estp_title, R.string.estp_discription),
    INFJ("INFJ",R.drawable.img_login_infj, R.string.infj_title, R.string.infj_discription),
    INFP("INFP",R.drawable.img_login_infp, R.string.infp_title, R.string.infp_discription),
    INTJ("INTJ",R.drawable.img_login_intj, R.string.intj_title, R.string.intj_discription),
    INTP("INTP",R.drawable.img_login_intp, R.string.intp_title, R.string.intp_discription),
    ISFJ("ISFJ",R.drawable.img_login_isfj, R.string.isfj_title, R.string.isfj_discription),
    ISFP("ISFP",R.drawable.img_login_isfp, R.string.isfp_title, R.string.isfp_discription),
    ISTJ("ISTJ",R.drawable.img_login_istj, R.string.istj_title, R.string.istj_discription),
    ISTP("ISTP",R.drawable.img_login_istp, R.string.istp_title, R.string.istp_discription)
}