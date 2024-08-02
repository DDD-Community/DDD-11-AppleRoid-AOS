package com.appleroid.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.appleroid.core.designsystem.component.CircleImage
import com.appleroid.core.designsystem.component.LabelText
import com.appleroid.core.designsystem.component.MbtiCard
import com.appleroid.core.designsystem.theme.MBTI_ENFP

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