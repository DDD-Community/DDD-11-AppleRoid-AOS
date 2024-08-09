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
import com.appleroid.model.FeedButtonItem
import com.appleroid.model.FeedCardResources
import com.appleroid.model.FeedInfo
import com.appleroid.model.FeedInfoItem

@Composable
fun FeedCard(
    feedInfoItem: FeedInfoItem,
    feedCardResources: FeedCardResources,
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
                moreImageRes = feedCardResources.moreImageRes
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
                        painter = painterResource(feedCardResources.eclipseStartImageRes),
                        contentDescription = "eclipse start image"
                    )

                    Spacer(modifier.weight(1F))

                    Image(
                        painter = painterResource(feedCardResources.eclipseEndImageRes),
                        contentDescription = "eclipse end image"
                    )
                }
            }

            FeedContent(
                feedInfoItem = feedInfoItem,
                plusImageRes = feedCardResources.plusImageRes,
                likeImageRes = feedCardResources.likeImageRes,
                unLikeImageRes = feedCardResources.unLikeImageRes,
                commentImageRes = feedCardResources.commentImageRes,
                voteImageRes = feedCardResources.voteImageRes,
                mbtiStringRes = feedCardResources.mbtiStringRes,
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
    plusImageRes: Int,
    likeImageRes: Int,
    unLikeImageRes: Int,
    commentImageRes: Int,
    voteImageRes: Int,
    mbtiStringRes: String,
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
                selectedImageRes = plusImageRes,
                unselectedImageRes = plusImageRes,
                text = mbtiStringRes,
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
                    selectedImageRes = likeImageRes,
                    unselectedImageRes = unLikeImageRes,
                    text = "145",
                    interval = 8.dp,
                    arrangement = Arrangement.Start,
                    onClick = {

                    }
                )

                ImageWithTextBtn(
                    modifier = modifier.weight(weight = 1F, fill = false),
                    selectedImageRes = commentImageRes,
                    unselectedImageRes = commentImageRes,
                    text = "37",
                    interval = 8.dp,
                    arrangement = Arrangement.Center,
                    onClick = {

                    }
                )

                ImageWithTextBtn(
                    modifier = modifier.weight(weight = 1F, fill = false),
                    selectedImageRes = voteImageRes,
                    unselectedImageRes = voteImageRes,
                    text = "705표",
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
