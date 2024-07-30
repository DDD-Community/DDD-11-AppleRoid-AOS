package com.appleroid.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.appleroid.core.designsystem.R
import com.appleroid.core.designsystem.theme.GREY05
import com.appleroid.core.designsystem.theme.POINT01
import com.appleroid.core.designsystem.theme.WHITE

@Composable
fun WithTextCheckBox(
    modifier: Modifier = Modifier,
    isSelected : Boolean = false,
    text : String = "",
    onSelected : (Boolean) -> Unit
){
    Box(
        modifier = modifier
            .background(
                color = GREY05,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = if(isSelected) POINT01 else GREY05,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onSelected(!isSelected) }
    ){
        Row(
            modifier = Modifier.align(Alignment.CenterStart)
        ){
            Spacer(modifier = Modifier.width(16.dp))

            Image(
                painter = painterResource(if(isSelected) R.drawable.ic_select_check else R.drawable.ic_unselect_check),
                contentDescription = "icon_check",
                modifier = Modifier.size(16.dp)
            )

            Text(
                text = text,
                color = if(isSelected) POINT01 else WHITE,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }
}