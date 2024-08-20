package com.appleroid.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.appleroid.core.designsystem.theme.GREY03
import com.appleroid.core.designsystem.theme.GREY05

@Composable
fun VoteStatisticsRow(
    modifier: Modifier = Modifier,
    mbtiType: String,
    backgroundColor: Color = GREY05,
    voteColor: Color = GREY03,
    votePercentage: Int,
    voteCount: Int,
    unitLabel: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(35.dp)
            .fillMaxWidth()
    ) {
        LabelText(
            modifier = Modifier.width(44.dp),
            text = mbtiType,
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )

        Spacer(modifier = Modifier.width(8.dp))

        VoteBar(
            modifier = Modifier.weight(1f),
            backgroundColor = backgroundColor,
            voteColor = voteColor,
            votePercentage = votePercentage
        )

        Spacer(modifier = Modifier.width(8.dp))

        LabelText(
            modifier = Modifier.wrapContentSize(),
            text = "$voteCount$unitLabel",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )
    }
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
fun VoteBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    voteColor: Color,
    votePercentage: Int
) {
    Box(
        modifier = modifier
            .height(11.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(votePercentage * 0.01f)
                .height(11.dp)
                .background(
                    color = voteColor,
                    shape = RoundedCornerShape(16.dp)
                )
        )
    }
}
