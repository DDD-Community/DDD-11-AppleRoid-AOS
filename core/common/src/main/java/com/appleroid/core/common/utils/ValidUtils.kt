package com.appleroid.core.common.utils

import java.util.regex.Pattern

fun isValidPhoneNumber(phone: String): Boolean {
    val phoneRegex = "^(01[016789])(\\d{3,4})(\\d{4})$"
    val pattern = Pattern.compile(phoneRegex)
    val matcher = pattern.matcher(phone)
    return matcher.matches()
}

fun isValidBirthDate(birthDate: String): Boolean {
    val birthDateRegex = "^\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])$"
    val pattern = Pattern.compile(birthDateRegex)
    val matcher = pattern.matcher(birthDate)
    return matcher.matches()
}

fun isValidResidentNumberFirstDigit(digit: String): Boolean {
    val validDigits = setOf("1", "2", "3", "4")
    return digit in validDigits
}

fun isValidKoreanName(name: String): Boolean {
    val nameRegex = "^[가-힣]{2,}$"
    val pattern = Pattern.compile(nameRegex)
    val matcher = pattern.matcher(name)
    if (!matcher.matches()) return false

    // 초성만 있는지 체크
    val consonants = "ㄱ-ㅎ"
    val consonantPattern = Pattern.compile("[$consonants]+")
    val consonantMatcher = consonantPattern.matcher(name)
    return !consonantMatcher.matches()
}