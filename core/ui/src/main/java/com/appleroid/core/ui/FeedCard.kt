package com.appleroid.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.appleroid.core.designsystem.component.CircleImage
import com.appleroid.core.designsystem.component.DescriptionText
import com.appleroid.core.designsystem.component.DottedDivider
import com.appleroid.core.designsystem.component.ImageWithTextBtn
import com.appleroid.core.designsystem.component.LabelText
import com.appleroid.core.designsystem.component.MbtiCard
import com.appleroid.core.designsystem.component.TitleText
import com.appleroid.core.designsystem.component.WithTextCheckBoxCard
import com.appleroid.core.designsystem.theme.GREY03
import com.appleroid.core.designsystem.theme.GREY04
import com.appleroid.core.designsystem.theme.GREY06
import com.appleroid.core.designsystem.theme.MBTI_ENFP
import com.appleroid.core.designsystem.utils.toCommaString
import com.appleroid.model.FeedButtonItem
import com.appleroid.model.FeedInfoItem

@Composable
fun FeedCard(
    feedInfoItem: FeedInfoItem,
    oneSelected: Boolean,
    onOneSelected: (Boolean) -> Unit,
    twoSelected: Boolean,
    onTwoSelected: (Boolean) -> Unit,
    onMbtiResultClicked: (Int) -> Unit,
    onMoreClicked: () -> Unit,
    likeClicked: (Boolean) -> Unit,
    commentClicked: () -> Unit,
    voteClicked: () -> Unit,
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
                feedInfoItem = feedInfoItem,
                onMoreClicked = {
                    onMoreClicked()
                }
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentAlignment = Alignment.Center
            ) {

                DottedDivider()

                Row(
                    modifier = Modifier.fillMaxWidth(),
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
                onMbtiResultClicked = onMbtiResultClicked,
                oneSelected = oneSelected,
                onOneSelected = onOneSelected,
                twoSelected = twoSelected,
                onTwoSelected = onTwoSelected,
                likeClicked = likeClicked,
                commentClicked = commentClicked,
                voteClicked = voteClicked
            )
        }
    }
}

@Composable
fun FeedContent(
    feedInfoItem: FeedInfoItem,
    onMbtiResultClicked: (Int) -> Unit,
    oneSelected: Boolean,
    onOneSelected: (Boolean) -> Unit,
    twoSelected: Boolean,
    onTwoSelected: (Boolean) -> Unit,
    likeClicked: (Boolean) -> Unit,
    commentClicked: () -> Unit,
    voteClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(24.dp)
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

            CheckBoxGroup(
                oneSelected = oneSelected,
                onOneSelected = onOneSelected,
                twoSelected = twoSelected,
                onTwoSelected = onTwoSelected,
                buttonItems = feedInfoItem.buttonItems
            )

            Spacer(modifier = Modifier.height(16.dp))

            ImageWithTextBtn(
                modifier = Modifier,
                selectedImageRes = R.drawable.ic_plus,
                unselectedImageRes = R.drawable.ic_plus,
                text = stringResource(R.string.feature_home_feed_mbti_result),
                interval = 2.dp,
                arrangement = Arrangement.End,
                onClick = {
                    onMbtiResultClicked(feedInfoItem.id)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(GREY04)
            )

            ActionButtonsRow(
                likeClicked = likeClicked,
                commentClicked = commentClicked,
                voteClicked = voteClicked
            )
        }
    }
}

@Composable
fun UserInfoRow(
    onMoreClicked: () -> Unit,
    feedInfoItem: FeedInfoItem,
    modifier: Modifier = Modifier) {
    Row(modifier.padding(start = 24.dp, end = 24.dp, top = 24.dp)) {
        CircleImage(
            modifier = Modifier.align(Alignment.CenterVertically),
            imageRes = feedInfoItem.profileImageRes,
            contentDescription = "profile image"
        )

        Column(modifier = Modifier
            .wrapContentSize()
            .padding(start = 12.dp)
        ) {
            Row {
                LabelText(
                    modifier = Modifier
                        .height(27.dp)
                        .align(Alignment.Top),
                    text = feedInfoItem.nickName,
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White
                )

                MbtiCard(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 4.dp),
                    text = feedInfoItem.mbti,
                    cardColor = MBTI_ENFP
                )
            }

            LabelText(
                text = feedInfoItem.time,
                style = MaterialTheme.typography.bodySmall,
                color = GREY03
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Image(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .clickable {
                    onMoreClicked()
                },
            painter = painterResource(R.drawable.ic_home_contents_more),
            contentDescription = "more image"
        )
    }
}

@Composable
fun CheckBoxGroup(
    oneSelected: Boolean,
    onOneSelected: (Boolean) -> Unit,
    twoSelected: Boolean,
    onTwoSelected: (Boolean) -> Unit,
    buttonItems: List<FeedButtonItem>,
    modifier: Modifier = Modifier
) {
    WithTextCheckBoxCard(
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth(),
        text = buttonItems[0].title,
        isSelected = oneSelected,
        onSelected = {
            onOneSelected(it)
            onTwoSelected(!it)
        }
    )

    Spacer(modifier = Modifier.height(8.dp))

    WithTextCheckBoxCard(
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth(),
        text = buttonItems[1].title,
        isSelected = twoSelected,
        onSelected = {
            onTwoSelected(it)
            onOneSelected(!it)
        }
    )
}

@Composable
fun ActionButtonsRow(
    modifier: Modifier = Modifier,
    likeClicked: (Boolean) -> Unit,
    commentClicked: () -> Unit,
    voteClicked: () -> Unit
) {
    Row(modifier = Modifier.padding(top = 16.dp)) {
        ImageWithTextBtn(
            modifier = modifier.weight(1F, false),
            selectedImageRes = R.drawable.ic_like,
            unselectedImageRes = R.drawable.ic_un_like,
            text = "2145".toCommaString(),
            interval = 8.dp,
            arrangement = Arrangement.Start,
            onClick = {
                likeClicked(it)
            }
        )

        ImageWithTextBtn(
            modifier = modifier.weight(1F, false),
            selectedImageRes = R.drawable.ic_comment,
            unselectedImageRes = R.drawable.ic_comment,
            text = "37".toCommaString(),
            interval = 8.dp,
            arrangement = Arrangement.Center,
            onClick = {
                commentClicked()
            }
        )

        ImageWithTextBtn(
            modifier = modifier.weight(1F, false),
            selectedImageRes = R.drawable.ic_vote,
            unselectedImageRes = R.drawable.ic_vote,
            text = "${1428.toCommaString()}표",
            interval = 8.dp,
            arrangement = Arrangement.End,
            onClick = {
                voteClicked()
            }
        )
    }
}
