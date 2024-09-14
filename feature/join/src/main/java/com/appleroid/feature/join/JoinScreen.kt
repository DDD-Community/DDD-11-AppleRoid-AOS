package com.appleroid.feature.join

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.appleroid.core.designsystem.component.CheckTextField
import com.appleroid.core.designsystem.component.DescriptionText
import com.appleroid.core.designsystem.component.LabelBtn
import com.appleroid.core.designsystem.component.LabelText
import com.appleroid.core.designsystem.component.MKungBtn
import com.appleroid.core.designsystem.component.MKungTextField
import com.appleroid.core.designsystem.component.MbtiCheckBox
import com.appleroid.core.designsystem.component.TitleText
import com.appleroid.core.designsystem.component.TopAppBar
import com.appleroid.core.designsystem.component.WithTextCheckBoxCard
import com.appleroid.core.designsystem.theme.DOT
import com.appleroid.core.designsystem.theme.GREY01
import com.appleroid.core.designsystem.theme.GREY06
import com.appleroid.core.designsystem.theme.GREY07
import com.appleroid.core.designsystem.theme.GREY08
import com.appleroid.core.designsystem.utils.PhoneNumberVisualTransformation
import com.appleroid.core.designsystem.utils.keyboardAsState
import com.appleroid.feature.join.model.BottomSheetType
import com.appleroid.feature.join.model.CarrierType
import com.appleroid.feature.join.model.ENERGY_DIRECTION
import com.appleroid.feature.join.model.InputType
import com.appleroid.feature.join.model.JUDGMENT
import com.appleroid.feature.join.model.MbtiResult
import com.appleroid.feature.join.model.MbtiType
import com.appleroid.feature.join.model.PagerType
import com.appleroid.feature.join.model.RECOGNIZE
import com.appleroid.feature.join.model.TermType
import kotlinx.coroutines.launch

@Composable
fun JoinRoute(
    modifier: Modifier = Modifier,
    joinCompleteClicked: () -> Unit,
) {
    JoinScreen(
        modifier = modifier,
        joinCompleteClicked = joinCompleteClicked
    )
}

@Composable
fun JoinScreen(
    modifier: Modifier = Modifier,
    joinCompleteClicked: () -> Unit
) {
    val pagerState = rememberPagerState { 4 }
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    val isKeyboardOpen by keyboardAsState()

    var selectedAllTerm by remember { mutableStateOf(false) }
    var selectedPrivacyTerm by remember { mutableStateOf(false) }
    var selectedServiceTerm by remember { mutableStateOf(false) }
    var bottomBtnEnable by remember { mutableStateOf(false) }
    var selectedEnergyDirection by remember { mutableStateOf<MbtiType?>(null) }
    var selectedRecognize by remember { mutableStateOf<MbtiType?>(null) }
    var selectedJudgment by remember { mutableStateOf<MbtiType?>(null) }
    var selectedPlanning by remember { mutableStateOf<MbtiType?>(null) }

    var isSendVerifyNumber by remember{ mutableStateOf(false) }

    var selectedCarrier by remember { mutableStateOf<CarrierType?>(null) }
    var phoneNumber by remember { mutableStateOf("") }
    var verifyNumber by remember { mutableStateOf("") }

    var nickname by remember { mutableStateOf("") }

    fun allAgreeCheck() {
        selectedAllTerm = selectedPrivacyTerm && selectedServiceTerm
    }

    LaunchedEffect(isKeyboardOpen) {
        if (isKeyboardOpen.not()) {
            focusManager.clearFocus()
        }
    }

    /*LaunchedEffect(
        pagerState.currentPage,
        selectedAllTerm,
        phoneNumber,
        registrationNumber,
        registrationSecondNumber,
        name,
        selectedCarrier
    ){
        bottomBtnEnable = when(pagerState.currentPage){
            PagerType.TERM_AGREE.index -> selectedAllTerm
            PagerType.PHONE_VERIFY.index -> {
                isValidPhoneNumber(phoneNumber)
                        && isValidBirthDate(registrationNumber)
                        && isValidResidentNumberFirstDigit(registrationSecondNumber)
                        && isValidKoreanName(name)
                        && selectedCarrier != null
            }
            PagerType.NICKNAME.index -> false
            else -> false
        }
    }
*/

    LaunchedEffect(
        pagerState.currentPage,
        selectedAllTerm,
        phoneNumber,
        selectedCarrier
    ) {
        bottomBtnEnable = when (pagerState.currentPage) {
            PagerType.PHONE_VERIFY.index -> true
            PagerType.TERM_AGREE.index -> true
            PagerType.NICKNAME.index -> true
            else -> true
        }
    }


    Box(
        modifier = modifier.fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {

            TopAppBar(
                title = stringResource(
                    when (pagerState.currentPage) {
                        PagerType.TERM_AGREE.index -> PagerType.TERM_AGREE.titleRes
                        PagerType.PHONE_VERIFY.index -> PagerType.PHONE_VERIFY.titleRes
                        PagerType.NICKNAME.index -> PagerType.NICKNAME.titleRes
                        else -> PagerType.MBTI.titleRes
                    }
                )
            )

            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false,
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ){
                    when (it) {
                        PagerType.PHONE_VERIFY.index -> {
                            PhoneVerifyScreen(
                                modifier = Modifier.align(Alignment.TopCenter),
                                isSendVerifyNumber = isSendVerifyNumber,
                                phoneNumber = phoneNumber,
                                verifyNumber = verifyNumber,
                                onChangedPhoneNumber = { newValue -> phoneNumber = newValue },
                                onChangedVerifyNumber = { newValue -> verifyNumber = newValue },
                                onClickVerifySend = { isSendVerifyNumber = true }
                            )
                        }

                        PagerType.TERM_AGREE.index -> {
                            TermScreen(
                                modifier = Modifier.align(Alignment.TopCenter),
                                selectedAllTerm = selectedAllTerm,
                                selectedPrivacyTerm = selectedPrivacyTerm,
                                selectedServiceTerm = selectedServiceTerm,
                                onChangedSelectAllTerm = { newValue ->
                                    selectedAllTerm = newValue
                                    selectedPrivacyTerm = newValue
                                    selectedServiceTerm = newValue
                                },
                                onChangedSelectPrivacyTerm = { newValue ->
                                    selectedPrivacyTerm = newValue
                                    allAgreeCheck()
                                },
                                onChangedSelectServiceTerm = { newValue ->
                                    selectedServiceTerm = newValue
                                    allAgreeCheck()
                                }
                            )
                        }

                        PagerType.NICKNAME.index -> {
                            NicknameScreen(
                                modifier = Modifier.align(Alignment.TopCenter),
                                nickname = nickname,
                                onChangeNickname = { newValue -> nickname = newValue }
                            )
                        }

                        else -> {
                            MbtiScreen(
                                modifier = Modifier.align(Alignment.TopCenter),
                                selectedEnergyDirection = selectedEnergyDirection,
                                selectedJudgment = selectedJudgment,
                                selectedRecognize = selectedRecognize,
                                selectedPlanning = selectedPlanning,
                                onClick = { mbti ->
                                    when (mbti.type) {
                                        ENERGY_DIRECTION -> selectedEnergyDirection = mbti
                                        RECOGNIZE -> selectedRecognize = mbti
                                        JUDGMENT -> selectedJudgment = mbti
                                        else -> selectedPlanning = mbti
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }

        MKungBtn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 28.dp)
                .height(48.dp)
                .align(Alignment.BottomCenter)
                .padding(horizontal = 20.dp),
            enable = bottomBtnEnable,
            text = stringResource(
                when (pagerState.currentPage) {
                    PagerType.TERM_AGREE.index -> PagerType.TERM_AGREE.bottomBtnRes
                    PagerType.PHONE_VERIFY.index -> PagerType.PHONE_VERIFY.bottomBtnRes
                    PagerType.NICKNAME.index -> PagerType.NICKNAME.bottomBtnRes
                    else -> PagerType.MBTI.bottomBtnRes
                }
            ),
        ) {
            scope.launch {
                when (pagerState.currentPage) {
                    PagerType.PHONE_VERIFY.index -> {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                    PagerType.MBTI.index -> {
                        joinCompleteClicked.invoke()
                    }
                    else -> {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            }
        }
    }
}

@Composable
fun TermScreen(
    modifier: Modifier = Modifier,
    selectedAllTerm: Boolean = false,
    selectedPrivacyTerm: Boolean = false,
    selectedServiceTerm: Boolean = false,
    onChangedSelectAllTerm: (Boolean) -> Unit,
    onChangedSelectPrivacyTerm: (Boolean) -> Unit,
    onChangedSelectServiceTerm: (Boolean) -> Unit,
) {
    val termList = TermType.entries

    Column(
        modifier = modifier
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        TitleText(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            title = stringResource(R.string.term_title)
        )

        Spacer(modifier = Modifier.height(12.dp))

        termList.forEach { type ->
            Spacer(modifier = Modifier.height(8.dp))

            WithTextCheckBoxCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                text = stringResource(type.stringRes),
                isSelected = when (type) {
                    TermType.ALL_TERM -> selectedAllTerm
                    TermType.PRIVACY_TERM -> selectedPrivacyTerm
                    TermType.SERVICE_TERM -> selectedServiceTerm
                },
                onSelected = {
                    when (type) {
                        TermType.ALL_TERM -> onChangedSelectAllTerm(it)
                        TermType.PRIVACY_TERM -> onChangedSelectPrivacyTerm(it)
                        TermType.SERVICE_TERM -> onChangedSelectServiceTerm(it)
                    }
                }
            )
        }
    }
}

@Composable
fun PhoneVerifyScreen(
    modifier: Modifier = Modifier,
    isSendVerifyNumber: Boolean,
    phoneNumber: String,
    verifyNumber: String,
    onChangedPhoneNumber: (String) -> Unit,
    onChangedVerifyNumber: (String) -> Unit,
    onClickVerifySend : () -> Unit
) {

    Column(
        modifier = modifier
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        TitleText(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            title = stringResource(R.string.phone_verify_title)
        )

        DescriptionText(
            modifier = Modifier
                .fillMaxWidth()
                .height(21.dp),
            title = stringResource(R.string.phone_verify_description)
        )

        Spacer(modifier = Modifier.height(28.dp))

        InputContent(
            inputType = InputType.PHONE_VERIFY,
            text = phoneNumber,
            onChangedText = onChangedPhoneNumber,
            onClick = onClickVerifySend
        )

        if(isSendVerifyNumber){
            Spacer(modifier = Modifier.height(18.dp))

            InputContent(
                inputType = InputType.VERIFY_NUMBER,
                text = verifyNumber,
                onChangedText = onChangedVerifyNumber,
                onClick = {}
            )
        }
    }
}

@Composable
fun NicknameScreen(
    modifier: Modifier = Modifier,
    nickname: String,
    onChangeNickname: (String) -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        TitleText(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            title = stringResource(R.string.nickname_title)
        )

        DescriptionText(
            modifier = Modifier
                .fillMaxWidth()
                .height(21.dp),
            title = stringResource(R.string.nickname_description)
        )

        Spacer(modifier = Modifier.height(23.dp))

        InputContent(
            inputType = InputType.NICKNAME,
            text = nickname,
            onChangedText = onChangeNickname,
            onClick = {}
        )
    }
}

@Composable
fun MbtiScreen(
    modifier: Modifier = Modifier,
    selectedEnergyDirection: MbtiType?,
    selectedRecognize: MbtiType?,
    selectedJudgment: MbtiType?,
    selectedPlanning: MbtiType?,
    onClick: (MbtiType) -> Unit
) {

    val screenWidth = LocalConfiguration.current.screenWidthDp

    val mbtiResult = mbtiCheck(
        selectedEnergyDirection = selectedEnergyDirection,
        selectedRecognize = selectedRecognize,
        selectedJudgment = selectedJudgment,
        selectedPlanning = selectedPlanning
    )

    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            TitleText(
                modifier = Modifier
                    .fillMaxWidth(),
                alignment = Alignment.TopCenter,
                textAlign = TextAlign.Center,
                title = mbtiResult?.let {
                    stringResource(it.titleRes)
                }?:run{
                    stringResource(R.string.mbti_title)
                }
            )

            Spacer(modifier = Modifier.height(5.dp))

            DescriptionText(
                modifier = Modifier
                    .fillMaxWidth(),
                title = mbtiResult?.let {
                    stringResource(it.descriptionRes)
                }?:run{
                    stringResource(R.string.mbti_description)
                },
                alignment = Alignment.TopCenter,
                textAlign = TextAlign.Center,
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height((screenWidth * 0.55).dp)
        ) {
            mbtiResult?.let {
                Image(
                    painter = painterResource(it.drawableRes),
                    contentDescription = "mbti_background",
                    modifier = Modifier.align(Alignment.Center)
                )
            }?:run{
                Image(
                    painter = painterResource(R.drawable.img_empty_mbti),
                    contentDescription = "mbti_image",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(154.dp)
                )

                Image(
                    painter = painterResource(R.drawable.img_empty_text),
                    contentDescription = "mbti_image",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 4.dp, start = 2.dp)
                )
            }
        }


        Spacer(modifier = Modifier.weight(1f))


        Row(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .padding(bottom = 86.dp)
        ) {
            repeat(4) {
                MbtiList(
                    modifier = Modifier.weight(1f),
                    itemList = when (it) {
                        ENERGY_DIRECTION -> listOf(MbtiType.E, MbtiType.I)
                        RECOGNIZE -> listOf(MbtiType.S, MbtiType.N)
                        JUDGMENT -> listOf(MbtiType.T, MbtiType.F)
                        else -> listOf(MbtiType.J, MbtiType.P)
                    },
                    selectedMbti = when (it) {
                        ENERGY_DIRECTION -> selectedEnergyDirection
                        RECOGNIZE -> selectedRecognize
                        JUDGMENT -> selectedJudgment
                        else -> selectedPlanning
                    },
                    onClick = { mbti ->
                        onClick(mbti)
                    }
                )
            }

        }
    }
}

@Composable
fun MbtiList(
    modifier: Modifier = Modifier,
    itemList: List<MbtiType>,
    selectedMbti: MbtiType?,
    onClick: (MbtiType) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        itemList.forEach {
            val isChecked = it == selectedMbti
            MbtiCheckBox(
                key = it.key,
                text = stringResource(it.stringRes),
                isChecked = isChecked,
                onClick = { key ->
                    onClick(MbtiType.valueOf(key))
                }
            )

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}


@Composable
fun InputContent(
    inputType : InputType,
    text : String,
    onChangedText : (String) -> Unit,
    onClick : () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LabelText(
            modifier = Modifier
                .height(18.dp),
            text = stringResource(inputType.titleRes),
            style = MaterialTheme.typography.labelSmall,
            color = GREY01
        )

        Spacer(modifier = Modifier.height(5.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp)
                .background(
                    color = GREY07,
                    shape = RoundedCornerShape(12.dp)
                )
        ){
            MKungTextField(
                modifier = Modifier
                    .padding(start = 17.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterStart),
                textStyle = MaterialTheme.typography.displayMedium,
                visualTransformation = if(inputType == InputType.PHONE_VERIFY) PhoneNumberVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    keyboardType = when(inputType){
                        InputType.PHONE_VERIFY, InputType.VERIFY_NUMBER -> KeyboardType.Number
                        InputType.NICKNAME -> KeyboardType.Text
                    }
                ),
                text = text,
                onChangedText = {
                    when(inputType){
                        InputType.PHONE_VERIFY -> if(it.length <= 11) onChangedText(it.replace(".","").replace(",",""))
                        InputType.VERIFY_NUMBER -> if(it.length <= 6) onChangedText(it.replace(".","").replace(",",""))
                        InputType.NICKNAME -> onChangedText(it.replace(" ",""))
                    }
                }
            )

            LabelBtn(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 14.dp)
                    .clickable { onClick() },
                text = stringResource(inputType.btnRes),
                textColor = GREY01,
                borderColor = GREY08,
                horizontalPadding = 9.dp
            )
        }
    }
}

private fun mbtiCheck(
    selectedEnergyDirection: MbtiType?,
    selectedRecognize: MbtiType?,
    selectedJudgment: MbtiType?,
    selectedPlanning: MbtiType?,
) : MbtiResult?{
    return if(selectedEnergyDirection != null && selectedRecognize != null && selectedJudgment != null && selectedPlanning != null){
        when{
            selectedEnergyDirection == MbtiType.E && selectedRecognize == MbtiType.S && selectedJudgment == MbtiType.T && selectedPlanning == MbtiType.J -> MbtiResult.ESTJ
            selectedEnergyDirection == MbtiType.I && selectedRecognize == MbtiType.S && selectedJudgment == MbtiType.T && selectedPlanning == MbtiType.J -> MbtiResult.ISTJ
            selectedEnergyDirection == MbtiType.E && selectedRecognize == MbtiType.S && selectedJudgment == MbtiType.F && selectedPlanning == MbtiType.J -> MbtiResult.ESFJ
            selectedEnergyDirection == MbtiType.I && selectedRecognize == MbtiType.S && selectedJudgment == MbtiType.F && selectedPlanning == MbtiType.J -> MbtiResult.ISFJ
            selectedEnergyDirection == MbtiType.E && selectedRecognize == MbtiType.S && selectedJudgment == MbtiType.T && selectedPlanning == MbtiType.P -> MbtiResult.ESTP
            selectedEnergyDirection == MbtiType.I && selectedRecognize == MbtiType.S && selectedJudgment == MbtiType.T && selectedPlanning == MbtiType.P -> MbtiResult.ISTP
            selectedEnergyDirection == MbtiType.E && selectedRecognize == MbtiType.S && selectedJudgment == MbtiType.F && selectedPlanning == MbtiType.P -> MbtiResult.ESFP
            selectedEnergyDirection == MbtiType.I && selectedRecognize == MbtiType.S && selectedJudgment == MbtiType.F && selectedPlanning == MbtiType.P -> MbtiResult.ISFP
            selectedEnergyDirection == MbtiType.E && selectedRecognize == MbtiType.N && selectedJudgment == MbtiType.T && selectedPlanning == MbtiType.J -> MbtiResult.ENTJ
            selectedEnergyDirection == MbtiType.I && selectedRecognize == MbtiType.N && selectedJudgment == MbtiType.T && selectedPlanning == MbtiType.J -> MbtiResult.INTJ
            selectedEnergyDirection == MbtiType.E && selectedRecognize == MbtiType.N && selectedJudgment == MbtiType.F && selectedPlanning == MbtiType.J -> MbtiResult.ENFJ
            selectedEnergyDirection == MbtiType.I && selectedRecognize == MbtiType.N && selectedJudgment == MbtiType.F && selectedPlanning == MbtiType.J -> MbtiResult.INFJ
            selectedEnergyDirection == MbtiType.E && selectedRecognize == MbtiType.N && selectedJudgment == MbtiType.T && selectedPlanning == MbtiType.P -> MbtiResult.ENTP
            selectedEnergyDirection == MbtiType.I && selectedRecognize == MbtiType.N && selectedJudgment == MbtiType.T && selectedPlanning == MbtiType.P -> MbtiResult.INTP
            selectedEnergyDirection == MbtiType.E && selectedRecognize == MbtiType.N && selectedJudgment == MbtiType.F && selectedPlanning == MbtiType.P -> MbtiResult.ENFP
            else -> MbtiResult.INFP
        }
    }else{
        null
    }
}