package com.appleroid.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.appleroid.core.designsystem.component.CircleImage
import com.appleroid.core.designsystem.component.ImageWithTextBtn
import com.appleroid.core.designsystem.component.LabelText
import com.appleroid.core.designsystem.component.MbtiCard
import com.appleroid.core.designsystem.theme.GREY02
import com.appleroid.core.designsystem.theme.GREY03
import com.appleroid.core.designsystem.theme.MBTI_ENFP
import com.appleroid.core.designsystem.utils.toCommaString
import com.appleroid.model.UserInfoRowItem

@Composable
fun UserInfoRow(
    userInfoRowItem: UserInfoRowItem,
    modifier: Modifier = Modifier
) {
    Row(modifier.padding(start = 24.dp, end = 24.dp, top = 24.dp)) {

        // 공통 CircleImage 추출
        CircleImage(
            modifier = when (userInfoRowItem) {
                is UserInfoRowItem.Alarm -> Modifier.padding(vertical = 5.dp)
                is UserInfoRowItem.Feed -> Modifier.align(Alignment.CenterVertically)
                else -> Modifier // Comment와 기본 처리
            },
            imageRes = userInfoRowItem.profileImageRes,
            contentDescription = "profile image"
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
                .padding(start = 12.dp)
        ) {
            Row {
                LabelText(
                    modifier = Modifier
                        .height(27.dp)
                        .align(Alignment.Top),
                    text = userInfoRowItem.nickName,
                    style = MaterialTheme.typography.labelLarge,
                    color = White
                )

                MbtiCard(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 4.dp),
                    text = userInfoRowItem.mbti,
                    cardColor = MBTI_ENFP
                )

                // Alarm에서 isComment 처리
                if (userInfoRowItem is UserInfoRowItem.Alarm && userInfoRowItem.isComment) {
                    Spacer(modifier = Modifier.width(4.dp))
                    LabelText(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        text = "님의 댓글",
                        style = MaterialTheme.typography.labelMedium,
                        color = GREY02
                    )
                }
            }

            // RowItem 타입에 따른 내용 처리
            ContentByType(userInfoRowItem)
        }

        // 우측 아이템 처리
        RightActionColumn(userInfoRowItem)
    }
}

@Composable
private fun ContentByType(userInfoRowItem: UserInfoRowItem) {
    when (userInfoRowItem) {
        is UserInfoRowItem.Alarm -> {
            if (userInfoRowItem.content.isNotBlank()) {
                LabelText(
                    text = userInfoRowItem.content,
                    style = MaterialTheme.typography.bodySmall,
                    color = White
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            LabelText(
                text = userInfoRowItem.time,
                style = MaterialTheme.typography.bodySmall,
                color = GREY03
            )
        }

        is UserInfoRowItem.Comment -> {
            LabelText(
                text = userInfoRowItem.content,
                style = MaterialTheme.typography.bodySmall,
                color = White
            )
            Spacer(modifier = Modifier.height(12.dp))
            LabelText(
                text = "${userInfoRowItem.time} · ${stringResource(R.string.feature_home_feed_report)}",
                style = MaterialTheme.typography.bodySmall,
                color = GREY03
            )
            Spacer(modifier = Modifier.height(8.dp))
            CommentReplyRow(userInfoRowItem)
        }

        is UserInfoRowItem.Feed -> {
            LabelText(
                text = userInfoRowItem.time,
                style = MaterialTheme.typography.bodySmall,
                color = GREY03
            )
        }
    }
}

@Composable
private fun CommentReplyRow(userInfoRowItem: UserInfoRowItem.Comment) {
    Row {
        Box(
            modifier = Modifier
                .width(16.dp)
                .height(2.dp)
                .align(Alignment.CenterVertically)
                .background(
                    color = GREY02,
                    shape = RoundedCornerShape(32.dp)
                )
        )
        Spacer(modifier = Modifier.width(8.dp))
        LabelText(
            text = userInfoRowItem.reply,
            style = MaterialTheme.typography.bodySmall,
            color = GREY03
        )
    }
}

@Composable
private fun RightActionColumn(userInfoRowItem: UserInfoRowItem) {
    Row {
        when (userInfoRowItem) {
            is UserInfoRowItem.Alarm -> Spacer(modifier = Modifier.height(20.dp))

            is UserInfoRowItem.Comment -> {
                Column(
                    modifier = Modifier
                        .width(44.dp) // 우측 Column이 44.dp를 차지하도록 고정
                        .padding(top = 29.dp)
                        .fillMaxHeight()
                ) {
                    ImageWithTextBtn(
                        selectedImageRes = R.drawable.ic_like,
                        unselectedImageRes = R.drawable.ic_un_like,
                        text = userInfoRowItem.liked.toCommaString(),
                        interval = 8.dp,
                        orientation = Orientation.Vertical,
                        arrangement = Arrangement.Center,
                        onClick = {
                            if (it) userInfoRowItem.onLikeSelected(userInfoRowItem.liked + 1)
                            else userInfoRowItem.onLikeSelected(userInfoRowItem.liked - 1)
                        }
                    )
                }
            }

            is UserInfoRowItem.Feed -> {
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable {
                            userInfoRowItem.onMoreClicked()
                        },
                    painter = painterResource(R.drawable.ic_home_contents_more),
                    contentDescription = "more image"
                )
            }
        }
    }
}
