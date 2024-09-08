package com.appleroid.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.appleroid.core.designsystem.theme.GREY03
import com.appleroid.core.designsystem.theme.WHITE

@Composable
fun ReportButtonSheet(
    onChangeShowReportSelectScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 28.dp)
    ) {
        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(
                    color = GREY03,
                    shape = RoundedCornerShape(12.dp)
                ),
            content = {
                Text(
                    text = stringResource(R.string.feature_home_feed_reporting),
                    style = MaterialTheme.typography.titleSmall,
                    color = WHITE
                )
            },
            onClick = onChangeShowReportSelectScreen
        )
    }
}