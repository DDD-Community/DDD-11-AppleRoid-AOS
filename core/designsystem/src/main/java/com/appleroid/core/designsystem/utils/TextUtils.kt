package com.appleroid.core.designsystem.utils

import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

val Dp.textSp: TextUnit
    @Composable get() = with(LocalDensity.current) {
        this@textSp.toSp()
    }

fun Int.toDp(): Dp {
    return (this / Resources.getSystem().displayMetrics.density).dp
}
class PhoneNumberVisualTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {

        val formattedText = formatPhoneNumber(text.text.replace(".","").replace(",",""))

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 2) return offset
                if (offset <= 6) return offset + 1
                return offset + 2
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 3) return offset
                if (offset <= 7) return offset - 1
                return offset - 2
            }
        }
        return TransformedText(AnnotatedString(formattedText), offsetMapping)
    }
}


fun formatPhoneNumber(number: String): String {
    val digits = number.filter { it.isDigit() }
    val builder = StringBuilder()

    for (i in digits.indices) {
        builder.append(digits[i])
        if (i == 2 || i == 6) builder.append('-')
    }

    return builder.toString()
}

fun Char.isKorean(): Boolean {
    val unicodeBlock = Character.UnicodeBlock.of(this)
    return unicodeBlock == Character.UnicodeBlock.HANGUL_SYLLABLES ||
            unicodeBlock == Character.UnicodeBlock.HANGUL_JAMO ||
            unicodeBlock == Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO ||
            unicodeBlock == Character.UnicodeBlock.HANGUL_JAMO_EXTENDED_A ||
            unicodeBlock == Character.UnicodeBlock.HANGUL_JAMO_EXTENDED_B
}

fun Int.toCommaString() = "%,d".format(this)
fun String.toCommaString() = this.toIntOrNull()?.toCommaString() ?: this // 문자열이 숫자인지 확인하고, 숫자인 경우에만 콤마 추가