import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
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
            dragHandle = null,
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(R.color.color_grey06))
                    .padding(16.dp)
            ) {
                Text(
                    text = "This is the bottom sheet content\nThis is the bottom sheet content\nThis is the bottom sheet content",
                    style = TextStyle(color = Color.White)
                )
            }
        }
    }
}