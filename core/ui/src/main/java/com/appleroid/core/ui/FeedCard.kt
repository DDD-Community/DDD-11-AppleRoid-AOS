package com.appleroid.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.appleroid.core.designsystem.theme.GREY03
import com.appleroid.core.designsystem.theme.GREY06

@Composable
fun FeedCard(
    modifier: Modifier = Modifier,
    profileImageRes: Int,
    moreImageRes: Int,
    nickName: String,
    mbti: String,
    time: String,
    feedTitle: String,
    feedContent: String,
    buttonTitleList: List<String>,
    buttonPercentList: List<Int>,
    boxRow: @Composable BoxScope.() -> Unit,
    resultRow: @Composable RowScope.() -> Unit,
    snsRow: @Composable RowScope.() -> Unit
) {
    var buttonOneSelected by remember { mutableStateOf(false) }
    var buttonTwoSelected by remember { mutableStateOf(false) }

    Card (modifier = modifier
        .fillMaxSize()
        .padding(top = 24.dp),
        colors = CardColors(
            containerColor = GREY06,
            contentColor = GREY06,
            disabledContentColor = GREY06,
            disabledContainerColor = GREY06
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column {
            UserInfoRow(
                modifier = modifier,
                profileImageRes = profileImageRes,
                nickName = nickName,
                mbti = mbti,
                time = time,
                moreImageRes = moreImageRes
            )

            Box (modifier.fillMaxWidth()) {
                boxRow()
            }

            FeedContent(
                modifier = modifier.fillMaxSize(),
                feedTitle = feedTitle,
                feedContent = feedContent,
                buttonTitleList = buttonTitleList,
                buttonPercentList = buttonPercentList,
                buttonOneSelected = buttonOneSelected,
                onChangedOneSelectButton = {
                    buttonOneSelected = it
                },
                buttonTwoSelected = buttonTwoSelected,
                onChangedTwoSelectButton = {
                    buttonTwoSelected = it
                },
                resultRow = resultRow,
                snsRow = snsRow
            )
        }
    }
}