package com.appleroid.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.appleroid.core.designsystem.component.CircleImage
import com.appleroid.core.designsystem.component.DescriptionText
import com.appleroid.core.designsystem.component.LabelText
import com.appleroid.core.designsystem.component.MbtiCard
import com.appleroid.core.designsystem.component.TitleText
import com.appleroid.core.designsystem.component.WithTextCheckBox
import com.appleroid.core.designsystem.theme.GREY03
import com.appleroid.core.designsystem.theme.GREY04
import com.appleroid.core.designsystem.theme.GREY06
import com.appleroid.core.designsystem.theme.MBTI_ENFP

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

@Composable
fun FeedContent(
    modifier: Modifier,
    feedTitle: String,
    feedContent: String,
    buttonTitleList: List<String>,
    buttonPercentList: List<Int>,
    buttonOneSelected: Boolean,
    onChangedOneSelectButton: (Boolean) -> Unit,
    buttonTwoSelected: Boolean,
    onChangedTwoSelectButton: (Boolean) -> Unit,
    resultRow: @Composable RowScope.() -> Unit,
    snsRow: @Composable RowScope.() -> Unit
) {
    Box (modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier.padding(start = 24.dp, top = 20.dp, end = 24.dp, bottom = 20.dp)
        ) {
            TitleText(
                modifier = modifier.fillMaxWidth(),
                title = feedTitle
            )

            DescriptionText(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 20.dp),
                title = feedContent
            )

            WithTextCheckBox(
                modifier = modifier
                    .height(48.dp)
                    .fillMaxWidth(),
                isSelected = buttonOneSelected,
                text = buttonTitleList[0],
                percentText = "${buttonPercentList[0]}%",
                onSelected = onChangedOneSelectButton
            )

            Spacer(modifier = modifier.height(8.dp))

            WithTextCheckBox(
                modifier = modifier
                    .height(48.dp)
                    .fillMaxWidth(),
                isSelected = buttonTwoSelected,
                text = buttonTitleList[1],
                percentText = "${buttonPercentList[1]}%",
                onSelected = onChangedTwoSelectButton
            )

            Row(modifier.padding(top = 16.dp, bottom = 16.dp)) {
                resultRow()
            }

            Spacer(
                modifier = modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(GREY04)
            )

            Row(modifier.padding(top = 16.dp)) {
                snsRow()
            }
        }
    }
}

@Composable
fun UserInfoRow(
    modifier: Modifier,
    profileImageRes: Int,
    nickName: String,
    mbti: String,
    time: String,
    moreImageRes: Int
) {
    Row(modifier.padding(start = 24.dp, end = 24.dp, top = 24.dp)) {
        CircleImage(
            modifier = modifier.align(Alignment.CenterVertically),
            imageRes = profileImageRes,
            contentDescription = "profile image"
        )

        Column(modifier = modifier.wrapContentSize().padding(start = 12.dp)) {
            Row {
                LabelText(
                    modifier = modifier.height(27.dp).align(Alignment.Top),
                    text = nickName,
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White
                )

                MbtiCard(
                    modifier = modifier.align(Alignment.CenterVertically).padding(start = 4.dp),
                    text = mbti,
                    cardColor = MBTI_ENFP
                )
            }

            LabelText(
                modifier = Modifier,
                text = time,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray // GREY03 색상으로 변경
            )
        }

        Spacer(modifier = modifier.weight(1f))

        Image(
            modifier = modifier.align(Alignment.CenterVertically),
            painter = painterResource(moreImageRes),
            contentDescription = "more image"
        )
    }
}