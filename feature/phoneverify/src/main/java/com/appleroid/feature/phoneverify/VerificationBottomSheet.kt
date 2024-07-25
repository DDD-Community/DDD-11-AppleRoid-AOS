import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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
    LaunchedEffect(sheetState) {
        if (!sheetState) setSheetState(false)
    }

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
                    modifier = Modifier.align(Alignment.TopEnd).padding(top = 16.dp, end = 16.dp),
                    painter = painterResource(R.drawable.ic_round_close),
                    contentDescription = "인증번호 입력 바텀 시트 닫기 이미지"
                )
                Column(
                    modifier = Modifier.padding(start = 25.dp, top = 25.dp, end = 25.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.bottom_sheet_title),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    )

                    Text(
                        modifier = Modifier.padding(top = 25.dp),
                        text = stringResource(R.string.bottom_sheet_verify_code),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = colorResource(R.color.color_grey01),
                            textAlign = TextAlign.Start
                        )
                    )

                    Row(modifier = Modifier.padding(top = 5.dp, bottom = 60.dp)) {
                        Box(
                            modifier = Modifier
                                .height(42.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(colorResource(R.color.card_background)),
                        ) {

                        }
                    }
                }
            }
        }
    }
}