package com.appleroid.feature.join

import android.util.Log
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.appleroid.core.designsystem.component.CheckTextField
import com.appleroid.core.designsystem.component.DescriptionText
import com.appleroid.core.designsystem.component.LabelText
import com.appleroid.core.designsystem.component.MKungBtn
import com.appleroid.core.designsystem.component.MKungTextField
import com.appleroid.core.designsystem.component.MbtiCheckBox
import com.appleroid.core.designsystem.component.TitleText
import com.appleroid.core.designsystem.component.TopAppBar
import com.appleroid.core.designsystem.component.WithTextCheckBox
import com.appleroid.core.designsystem.theme.DOT
import com.appleroid.core.designsystem.theme.GREY01
import com.appleroid.core.designsystem.theme.GREY04
import com.appleroid.core.designsystem.theme.GREY06
import com.appleroid.core.designsystem.utils.PhoneNumberVisualTransformation
import com.appleroid.core.designsystem.utils.keyboardAsState
import com.appleroid.feature.join.model.MbtiType
import com.appleroid.feature.join.model.BottomSheetType
import com.appleroid.feature.join.model.CarrierType
import com.appleroid.feature.join.model.ENERGY_DIRECTION
import com.appleroid.feature.join.model.JUDGMENT
import com.appleroid.feature.join.model.MbtiImage
import com.appleroid.feature.join.model.PagerType
import com.appleroid.feature.join.model.RECOGNIZE
import com.appleroid.feature.join.model.TermType
import kotlinx.coroutines.launch

@Composable
fun JoinRoute(
    modifier: Modifier = Modifier,
    joinCompleteClicked: () -> Unit,
) {
    JoinScreen(modifier, joinCompleteClicked)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JoinScreen(
    modifier: Modifier = Modifier,
    joinCompleteClicked: () -> Unit
) {
    val pagerState = rememberPagerState { 4 }
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState()
    val focusManager = LocalFocusManager.current
    val isKeyboardOpen by keyboardAsState()

    var selectedAllTerm by remember { mutableStateOf(false) }
    var selectedPrivacyTerm by remember { mutableStateOf(false) }
    var selectedServiceTerm by remember { mutableStateOf(false) }
    var isShowBottomSheet by remember { mutableStateOf(false) }
    var bottomBtnEnable by remember { mutableStateOf(false) }
    var selectedEnergyDirection by remember { mutableStateOf<MbtiType?>(null) }
    var selectedRecognize by remember { mutableStateOf<MbtiType?>(null) }
    var selectedJudgment by remember { mutableStateOf<MbtiType?>(null) }
    var selectedPlanning by remember { mutableStateOf<MbtiType?>(null) }

    var selectedCarrier by remember { mutableStateOf<CarrierType?>(null) }
    var phoneNumber by remember { mutableStateOf("") }
    var bottomSheetType by remember { mutableStateOf(BottomSheetType.CARRIER) }

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
                                selectedCarrier = selectedCarrier,
                                phoneNumber = phoneNumber,
                                onChangedPhoneNumber = { newValue -> phoneNumber = newValue },
                                onClickCarrier = {
                                    bottomSheetType = BottomSheetType.CARRIER
                                    isShowBottomSheet = true
                                }
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

        if (
            selectedEnergyDirection != null
            && selectedRecognize != null
            && selectedJudgment != null
            && selectedPlanning != null
        ){
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(394.dp)
                    .align(Alignment.TopCenter),
                painter = painterResource(R.drawable.img_mbti_selected),
                contentDescription = "img_mbti_selected",
                contentScale = ContentScale.FillBounds
            )
        }


        MKungBtn(
            modifier = Modifier
                .fillMaxWidth()
                .height(73.dp)
                .align(Alignment.BottomCenter)
                .padding(horizontal = 20.dp)
                .padding(bottom = 20.dp),
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
                        bottomSheetType = BottomSheetType.VERIFY
                        isShowBottomSheet = true
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

    if (isShowBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { isShowBottomSheet = false },
            sheetState = bottomSheetState,
            containerColor = GREY06,
            dragHandle = null
        ) {
            when (bottomSheetType) {
                BottomSheetType.CARRIER -> {
                    CarrierScreen(
                        onClickCarrier = {
                            selectedCarrier = it
                            isShowBottomSheet = false
                        },
                        onClose = { isShowBottomSheet = false }
                    )
                }

                BottomSheetType.VERIFY -> {
                    VerifyScreen(
                        onClose = {
                            isShowBottomSheet = false
                        },
                        onComplete = {
                            isShowBottomSheet = false
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    )
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
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        TitleText(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = 24.dp),
            title = stringResource(R.string.term_title)
        )

        Spacer(modifier = Modifier.height(12.dp))

        termList.forEach { type ->
            Spacer(modifier = Modifier.height(8.dp))

            WithTextCheckBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 24.dp),
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
    selectedCarrier: CarrierType?,
    phoneNumber: String,
    onChangedPhoneNumber: (String) -> Unit,
    onClickCarrier: () -> Unit
) {

    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        TitleText(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = 24.dp),
            title = stringResource(R.string.phone_verify_title)
        )

        DescriptionText(
            modifier = Modifier
                .fillMaxWidth()
                .height(21.dp)
                .padding(horizontal = 24.dp),
            title = stringResource(R.string.phone_verify_description)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 30.dp)
                .background(
                    color = GREY04,
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Spacer(modifier = Modifier.height(6.dp))

            PhoneVerifyInput(
                title = stringResource(R.string.phone_number),
                inputContent = {
                    Row {
                        LabelText(
                            modifier = Modifier
                                .height(27.dp)
                                .align(Alignment.CenterVertically)
                                .clickable { onClickCarrier() },
                            text = stringResource(selectedCarrier?.stringRes ?: R.string.carrier),
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.width(2.dp))

                        Image(
                            painter = painterResource(R.drawable.ic_dowm),
                            contentDescription = "down",
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )


                        MKungTextField(
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .align(Alignment.CenterVertically),
                            textStyle = MaterialTheme.typography.labelLarge,
                            placeholder = stringResource(R.string.phone_number_placeholder),
                            placeholderColor = GREY01,
                            visualTransformation = PhoneNumberVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                            text = phoneNumber,
                            onChangedText = {
                                if (it.isNotEmpty() && it.toIntOrNull() == null) return@MKungTextField
                                if (it.length < 12) onChangedPhoneNumber(it)
                            }
                        )
                    }
                }
            )

//            PhoneVerifyInput(
//                title = stringResource(R.string.registration_number),
//                inputContent = {
//                    Row{
//                        MKungTextField(
//                            modifier = Modifier
//                                .height(27.dp)
//                                .align(Alignment.CenterVertically),
//                            textStyle = MaterialTheme.typography.labelLarge,
//                            placeholder = stringResource(R.string.registration_number_placeholder),
//                            placeholderColor = GREY01,
//                            isLimitWidth = true,
//                            focusRequester = focusRequesterRegistration,
//                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
//                            text = registrationNumber,
//                            onChangedText = {
//                                if(it.isNotEmpty() && it.toIntOrNull() == null) return@MKungTextField
//                                if(it.length == 6) focusRequesterRegistrationSecond.requestFocus()
//                                if(it.length < 7) onChangedRegistrationNumber(it)
//                            }
//                        )
//
//                        LabelText(
//                            modifier = Modifier.padding(horizontal = 3.dp),
//                            text = stringResource(R.string.bar),
//                            style = MaterialTheme.typography.labelLarge,
//                            color = Color.White
//                        )
//
//                        MKungTextField(
//                            modifier = Modifier
//                                .height(27.dp)
//                                .align(Alignment.CenterVertically),
//                            textStyle = MaterialTheme.typography.labelLarge,
//                            placeholder = stringResource(R.string.registration_number_second_placeholder),
//                            placeholderColor = GREY01,
//                            isLimitWidth = true,
//                            focusRequester = focusRequesterRegistrationSecond,
//                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
//                            text = registrationSecondNumber,
//                            onChangedText = {
//                                if(it.isNotEmpty() && it.toIntOrNull() == null) return@MKungTextField
//                                if(it.length == 1) focusRequesterName.requestFocus()
//                                if(it.length < 2) onChangedRegistrationSecondNumber(it)
//                            }
//                        )
//
//                        repeat(6){
//                            Dot(modifier = Modifier.align(Alignment.CenterVertically))
//                        }
//                    }
//                }
//            )
//
//            PhoneVerifyInput(
//                title = stringResource(R.string.name),
//                inputContent = {
//                    MKungTextField(
//                        modifier = Modifier
//                            .height(27.dp),
//                        textStyle = MaterialTheme.typography.labelLarge,
//                        placeholder = stringResource(R.string.name_placeholder),
//                        placeholderColor = GREY01,
//                        focusRequester = focusRequesterName,
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
//                        text = name,
//                        onChangedText = {
//                            if(it.length < 8 && it.all { text -> text.isKorean() }) onChangedName(it)
//                        }
//                    )
//                }
//            )
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
        modifier = modifier.padding(horizontal = 24.dp)
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

        CheckTextField(
            text = nickname,
            onChangeText = {
                if (it.length < 11) onChangeNickname(it)
            },
            label = stringResource(R.string.nickname_label),
            btnImageRes = R.drawable.ic_valid
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

    val isAllChecked = selectedEnergyDirection != null
            && selectedRecognize != null
            && selectedJudgment != null
            && selectedPlanning != null

    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        TitleText(
            modifier = Modifier
                .fillMaxWidth()
                .height(59.dp),
            alignment = Alignment.TopCenter,
            title = stringResource(R.string.mbti_title)
        )

        DescriptionText(
            modifier = Modifier
                .fillMaxWidth()
                .height(21.dp),
            title = stringResource(R.string.mbti_description),
            alignment = Alignment.TopCenter
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(234.dp)
        ) {
            if (isAllChecked) {
                Image(
                    painter = painterResource(R.drawable.img_mbti_background),
                    contentDescription = "mbti_background",
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Image(
                painter = if (isAllChecked) {
                    val mbtiText = selectedEnergyDirection?.key + selectedRecognize?.key + selectedJudgment?.key + selectedPlanning?.key
                    painterResource(MbtiImage.valueOf(mbtiText).drawableRes)
                }else painterResource(R.drawable.img_empty_mbti),
                contentDescription = "mbti_image",
                modifier = Modifier.align(Alignment.Center)
            )
        }


        Spacer(modifier = Modifier.height(11.dp))

        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp)
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
                painter = painterResource(it.drawableRes),
                isChecked = isChecked,
                onClick = { key ->
                    onClick(MbtiType.valueOf(key))
                }
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun PhoneVerifyInput(
    modifier: Modifier = Modifier,
    title: String,
    inputContent: @Composable () -> Unit
) {

    Column(
        modifier = modifier.padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(11.dp))

        LabelText(
            modifier = Modifier
                .height(18.dp),
            text = title,
            style = MaterialTheme.typography.labelSmall,
            color = GREY01
        )

        inputContent()

        Spacer(modifier = Modifier.height(21.dp))
    }

}

@Composable
fun CarrierScreen(
    modifier: Modifier = Modifier,
    onClickCarrier: (CarrierType) -> Unit,
    onClose: () -> Unit
) {
    val list = CarrierType.entries

    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .align(Alignment.TopCenter)
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            LabelText(
                modifier = Modifier
                    .height(64.dp)
                    .align(Alignment.Start),
                text = stringResource(R.string.carrier_title),
                style = MaterialTheme.typography.labelLarge,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(13.dp))
            list.forEach {
                LabelText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                        .align(Alignment.Start)
                        .clickable { onClickCarrier(it) },
                    text = stringResource(it.stringRes),
                    style = MaterialTheme.typography.titleSmall,
                    color = GREY01
                )

                Spacer(modifier = Modifier.height(25.dp))
            }
        }

        Image(
            painter = painterResource(R.drawable.ic_close),
            contentDescription = "close",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp)
                .padding(end = 16.dp)
                .clickable { onClose() }
        )
    }
}

@Composable
fun VerifyScreen(
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    onComplete: () -> Unit
) {
    var verifyNumber by remember { mutableStateOf("") }

    Box(modifier = modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.height(25.dp))
            TitleText(
                modifier = Modifier.height(64.dp),
                title = stringResource(R.string.verify_title)
            )
            Spacer(modifier = Modifier.height(22.dp))

            CheckTextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                text = verifyNumber,
                onChangeText = {
                    if (it.isNotEmpty() && it.toIntOrNull() == null) return@CheckTextField
                    if (it.length < 7) verifyNumber = it
                    if (it.length == 6) onComplete()
                },
                label = stringResource(R.string.verify_number),
                btnImageRes = R.drawable.ic_resend,
                showTimer = true
            )

            Spacer(modifier = Modifier.height(60.dp))
        }

        Image(
            painter = painterResource(R.drawable.ic_close),
            contentDescription = "close",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp)
                .clickable { onClose() }
        )
    }
}

@Composable
fun Dot(
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Canvas(modifier = Modifier.size(5.dp)) {
            drawCircle(color = DOT, radius = 2.5.dp.toPx())
        }
        Spacer(modifier = Modifier.width(2.dp))
    }
}