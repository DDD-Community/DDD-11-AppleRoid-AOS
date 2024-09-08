package com.appleroid.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.appleroid.core.designsystem.component.CircleImage
import com.appleroid.core.designsystem.component.ImageWithTextBtn
import com.appleroid.core.designsystem.component.LabelText
import com.appleroid.core.designsystem.component.MbtiCard
import com.appleroid.core.designsystem.theme.GREY01
import com.appleroid.core.designsystem.theme.GREY02
import com.appleroid.core.designsystem.theme.GREY03
import com.appleroid.core.designsystem.theme.GREY05
import com.appleroid.core.designsystem.theme.MBTI_ENFP
import com.appleroid.core.designsystem.theme.WHITE
import com.appleroid.core.designsystem.utils.toCommaString

@Composable
fun FeedComment(
    isCommentSheet: MutableState<Boolean>,
    nickName: String,
    comment: String,
    mbti: String,
    time: String,
    replyText:String,
    profileImageRes: Int,
    liked: Int
) {
    // 클릭되면 isCommentSheet.value = true
    Row(
        modifier = Modifier
            .padding(start = 20.dp, top = 24.dp, end = 20.dp, bottom = 32.dp)
    ) {
        CircleImage(
            modifier = Modifier
                .width(36.dp)
                .height(36.dp),
            imageRes = profileImageRes,
            contentDescription = "user profile image"
        )

        Column(
            modifier = Modifier
                .weight(1f) // 가운데 Column이 남은 공간을 차지하도록 함
                .wrapContentSize()
                .padding(start = 12.dp)

        ) {
            Row {
                LabelText(
                    modifier = Modifier.height(19.dp),
                    text = nickName,
                    style = MaterialTheme.typography.bodyLarge,
                    color = WHITE
                )
                MbtiCard(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 4.dp),
                    text = mbti,
                    cardColor = MBTI_ENFP
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row {
                Column {
                    LabelText(
                        text = comment,
                        style = MaterialTheme.typography.bodySmall,
                        color = GREY01
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    LabelText(
                        text = "$time · ${stringResource(R.string.feature_home_feed_report)}",
                        style = MaterialTheme.typography.bodySmall,
                        color = GREY03
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row {
                        Box(
                            modifier = Modifier
                                .width(16.dp)
                                .height(2.dp)
                                .align(alignment = Alignment.CenterVertically)
                                .background(
                                    color = GREY02,
                                    shape = RoundedCornerShape(32.dp)
                                )
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        LabelText(
                            text = replyText,
                            style = MaterialTheme.typography.bodySmall,
                            color = GREY03
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.width(4.dp))

        Column(
            modifier = Modifier
                .width(44.dp) // 우측 Column이 44.dp를 차지하도록 고정
                .padding(top = 29.dp)
                .fillMaxHeight()
        ) {
            ImageWithTextBtn(
                selectedImageRes = R.drawable.ic_like,
                unselectedImageRes = R.drawable.ic_un_like,
                text = liked.toCommaString(),
                interval = 8.dp,
                orientation = Orientation.Vertical,
                arrangement = Arrangement.Center,
                onClick = {

                }
            )
        }
    }
}