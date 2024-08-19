package com.appleroid.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.appleroid.core.designsystem.R
import com.appleroid.core.designsystem.theme.GREY01
import com.appleroid.core.designsystem.theme.GREY02

@Composable
fun EmptyContent(
    modifier: Modifier = Modifier,
    imageSize: Dp = 120.dp,
    title: String,
    content: String? = null
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_feed_contents_empty),
            contentDescription = "No data available",
            modifier = Modifier.size(imageSize)
        )

        Spacer(Modifier.height(12.dp))

        LabelText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            alignment = Alignment.Center,
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = GREY01
        )

        content?.let {
            Spacer(Modifier.height(4.dp))

            LabelText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                alignment = Alignment.Center,
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                color = GREY02
            )
        }
    }
}