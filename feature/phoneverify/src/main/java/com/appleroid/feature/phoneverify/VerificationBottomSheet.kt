import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appleroid.feature.phoneverify.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationBottomSheet(
    sheetState: Boolean,
    setSheetState: (Boolean) -> Unit
) {
    var text by remember { mutableStateOf("") }

    /*LaunchedEffect(sheetState) {
        if (!sheetState) setSheetState(false)
    }*/

    if (sheetState) {
        ModalBottomSheet(
            onDismissRequest = { setSheetState(false) },
            modifier = Modifier.fillMaxWidth(),
            containerColor = colorResource(R.color.color_grey06),
            dragHandle = null,
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
        ) {
            Box {
                Image(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 16.dp, end = 16.dp)
                        .clickable {
                            setSheetState(false)
                        },
                    painter = painterResource(R.drawable.ic_round_close),
                    contentDescription = "인증번호 입력 바텀 시트 닫기 이미지"
                )
                Column(
                    modifier = Modifier.padding(start = 25.dp, top = 25.dp, end = 25.dp, bottom = 60.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.phone_verify_bottom_sheet_title),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    )

                    Text(
                        modifier = Modifier.padding(top = 25.dp, bottom = 5.dp),
                        text = stringResource(R.string.phone_verify_bottom_sheet_verify_code),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = colorResource(R.color.color_grey01),
                            textAlign = TextAlign.Start
                        )
                    )

                    Box(
                        modifier = Modifier
                            .height(42.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(colorResource(R.color.card_background))
                    ) {
                        Row (modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 14.dp, end = 14.dp)) {

                            BasicTextField(
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .align(Alignment.CenterVertically)
                                    .background(Color.Transparent),
                                value = text,
                                cursorBrush = SolidColor(Color.Transparent),
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.NumberPassword),
                                onValueChange = {
                                    if (it.length <= 6) {
                                        text = it
                                    }
                                },
                                textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
                                singleLine = true,
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            Text(
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(end = 6.dp),
                                text = "02:59",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = colorResource(R.color.button_border),
                                )
                            )

                            Card(
                                modifier = Modifier
                                    .width(50.dp)
                                    .height(23.dp)
                                    .border(
                                        1.dp,
                                        colorResource(R.color.color_border_grey),
                                        RoundedCornerShape(17.dp)
                                    )
                                    .align(Alignment.CenterVertically),

                                colors = CardDefaults.cardColors(
                                    containerColor = colorResource(R.color.card_background)
                                )
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Text(
                                        text = stringResource(R.string.phone_verify_re_send),
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Normal,
                                            color = Color.White,
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}