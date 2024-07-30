package com.appleroid.core.designsystem.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.appleroid.core.designsystem.theme.POINT01
import com.appleroid.core.designsystem.utils.textSp

@Composable
fun MKungTabRow(
    modifier: Modifier = Modifier,
    tabWidth: Dp,
    feedTypes: List<String>,
    selectedTab: (Int) -> Unit
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    TabRow(
        modifier = modifier.width(tabWidth).padding(start = 20.dp),
        selectedTabIndex = selectedTabIndex,
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                modifier = modifier
                    .tabIndicatorOffset(tabPositions[selectedTabIndex])
                    .width(tabPositions[selectedTabIndex].width)
                    .height(1.dp),
                color = POINT01
            )
        }
    ) {
        feedTypes.forEachIndexed { index, title ->
            Tab(modifier = modifier
                .wrapContentSize()
                .background(Black),
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    selectedTab(selectedTabIndex)
                },
                text = {
                    Text(
                        text = title,
                        color = if (selectedTabIndex == index) POINT01 else Color.Gray,
                        fontSize = 16.dp.textSp
                    )
                }
            )
        }
    }
}