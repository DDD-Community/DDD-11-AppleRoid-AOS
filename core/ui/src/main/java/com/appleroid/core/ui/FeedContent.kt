package com.appleroid.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appleroid.core.designsystem.component.DescriptionText
import com.appleroid.core.designsystem.component.TitleText
import com.appleroid.core.designsystem.component.WithTextCheckBox
import com.appleroid.core.designsystem.theme.GREY04

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
