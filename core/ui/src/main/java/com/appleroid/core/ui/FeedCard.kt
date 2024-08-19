package com.appleroid.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.appleroid.core.designsystem.component.*
import com.appleroid.core.designsystem.theme.*
import com.appleroid.core.designsystem.utils.toCommaString
import com.appleroid.model.FeedButtonItem
import com.appleroid.model.FeedCardResources
import com.appleroid.model.FeedInfo
import com.appleroid.model.FeedInfoItem

@Composable
fun FeedCard(
    feedInfoItem: FeedInfoItem,
    buttonOneSelected: Boolean,
    onButtonOneSelectedChange: (Boolean) -> Unit,
    buttonTwoSelected: Boolean,
    onButtonTwoSelectedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 24.dp),
        colors = CardDefaults.cardColors(
            containerColor = GREY06
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column {
            UserInfoRow(
                profileImageRes = feedInfoItem.profileImageRes,
                nickName = feedInfoItem.nickName,
                mbti = feedInfoItem.mbti,
                time = feedInfoItem.time,
                moreImageRes = R.drawable.ic_home_contents_more
            )

            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentAlignment = Alignment.Center
            ) {

                DottedDivider()

                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_eclipse_start),
                        contentDescription = "eclipse start image"
                    )

                    Spacer(modifier.weight(1F))

                    Image(
                        painter = painterResource(R.drawable.ic_eclipse_end),
                        contentDescription = "eclipse end image"
                    )
                }
            }

            FeedContent(
                feedInfoItem = feedInfoItem,
                buttonOneSelected = buttonOneSelected,
                onChangedOneSelectButton = onButtonOneSelectedChange,
                buttonTwoSelected = buttonTwoSelected,
                onChangedTwoSelectButton = onButtonTwoSelectedChange
            )
        }
    }
}

@Composable
fun FeedContent(
    feedInfoItem: FeedInfoItem,
    buttonOneSelected: Boolean,
    onChangedOneSelectButton: (Boolean) -> Unit,
    buttonTwoSelected: Boolean,
    onChangedTwoSelectButton: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier.padding(24.dp)
        ) {
            TitleText(
                modifier = Modifier.fillMaxWidth(),
                title = feedInfoItem.feedTitle
            )

            DescriptionText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                title = feedInfoItem.feedContent
            )

            WithTextCheckBox(
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth(),
                isSelected = buttonOneSelected,
                text = feedInfoItem.buttonItems[0].title,
                percentText = "${feedInfoItem.buttonItems[0].percent}%",
                onSelected = onChangedOneSelectButton
            )

            Spacer(modifier = Modifier.height(8.dp))

            WithTextCheckBox(
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth(),
                isSelected = buttonTwoSelected,
                text = feedInfoItem.buttonItems[1].title,
                percentText = "${feedInfoItem.buttonItems[1].percent}%",
                onSelected = onChangedTwoSelectButton
            )

            Spacer(modifier = Modifier.height(16.dp))

            ImageWithTextBtn(
                modifier = modifier,
                selectedImageRes = R.drawable.ic_plus,
                unselectedImageRes = R.drawable.ic_plus,
                text = stringResource(R.string.feature_home_feed_mbti_result),
                interval = 2.dp,
                arrangement = Arrangement.End,
                onClick = {

                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(GREY04)
            )

            Row(modifier = Modifier.padding(top = 16.dp)) {
                ImageWithTextBtn(
                    modifier = modifier.weight(weight = 1F, fill = false),
                    selectedImageRes = R.drawable.ic_like,
                    unselectedImageRes = R.drawable.ic_un_like,
                    text = "2145".toCommaString(),
                    interval = 8.dp,
                    arrangement = Arrangement.Start,
                    onClick = {

                    }
                )

                ImageWithTextBtn(
                    modifier = modifier.weight(weight = 1F, fill = false),
                    selectedImageRes = R.drawable.ic_comment,
                    unselectedImageRes = R.drawable.ic_comment,
                    text = "37".toCommaString(),
                    interval = 8.dp,
                    arrangement = Arrangement.Center,
                    onClick = {

                    }
                )

                ImageWithTextBtn(
                    modifier = modifier.weight(weight = 1F, fill = false),
                    selectedImageRes = R.drawable.ic_vote,
                    unselectedImageRes = R.drawable.ic_vote,
                    text = "${1428.toCommaString()}표",
                    interval = 8.dp,
                    arrangement = Arrangement.End,
                    onClick = {

                    }
                )
            }
        }
    }
}

@Composable
fun UserInfoRow(
    modifier: Modifier = Modifier,
    profileImageRes: Int,
    nickName: String,
    mbti: String,
    time: String,
    moreImageRes: Int
) {
    Row(modifier.padding(horizontal = 24.dp, vertical = 24.dp)) {
        CircleImage(
            modifier = Modifier.align(Alignment.CenterVertically),
            imageRes = profileImageRes,
            contentDescription = "profile image"
        )

        Column(modifier = Modifier
            .wrapContentSize()
            .padding(start = 12.dp)
        ) {
            Row {
                LabelText(
                    modifier = Modifier.height(27.dp).align(Alignment.Top),
                    text = nickName,
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White
                )

                MbtiCard(
                    modifier = Modifier.align(Alignment.CenterVertically).padding(start = 4.dp),
                    text = mbti,
                    cardColor = MBTI_ENFP
                )
            }

            LabelText(
                text = time,
                style = MaterialTheme.typography.bodySmall,
                color = GREY03
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Image(
            modifier = Modifier.align(Alignment.CenterVertically),
            painter = painterResource(moreImageRes),
            contentDescription = "more image"
        )
    }
}
