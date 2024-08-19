package com.appleroid.mkung.navigation

import com.appleroid.mkung.R

enum class TopDestination(
    val iconTextId : Int?,
    val selectedIcon: Int,
    val unselectedIcon: Int
) {
    HOME(
        iconTextId = R.string.icon_text_home,
        selectedIcon = R.drawable.ic_gnb_home_sel,
        unselectedIcon = R.drawable.ic_gnb_home_sel
    ),
    ASK(
        iconTextId = R.string.icon_text_ask,
        selectedIcon = R.drawable.ic_gnb_question_nor,
        unselectedIcon = R.drawable.ic_gnb_question_nor
    ),
    MY_PAGE(
        iconTextId = R.string.icon_text_my_page,
        selectedIcon = R.drawable.ic_gnb_mypage_nor,
        unselectedIcon = R.drawable.ic_gnb_mypage_nor
    )
}