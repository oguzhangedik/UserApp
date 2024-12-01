package com.example.userapp.core.extensions

import java.util.regex.Pattern

object RegexUtils {
    const val SEARCH_TEXT_REGEX = "[^a-zA-Z0-9\\s]"

    val EMAIL_ADDRESS: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
    )
    fun isValidTCKN(tcNo: String?): Boolean {
        if (tcNo.isNullOrEmpty()) {
            return false
        }

        val tc = tcNo.toLongOrNull()
        val isNumber = tc != null
        val hasElevenChar = tcNo.length == 11
        val isNotStartWithZero = tcNo[0] != '0'

        if (!isNumber || !hasElevenChar || !isNotStartWithZero) {
            return false
        }

        var first10NumberSum = 0
        var evenNumbersSum = 0
        var oddNumbersSum = 0

        for (i in tcNo.indices) {
            val n = tcNo[i].digitToInt()
            val isLastNo = i == 10
            if (!isLastNo) {
                first10NumberSum += n
            }
            val isEven = i % 2 == 0
            if (isEven && i <= 8) {
                evenNumbersSum += n
            }
            val isOdd = i % 2 != 0
            if (isOdd && i < 8) {
                oddNumbersSum += n
            }
        }

        val numberBeforeLastNumber = tcNo[9].digitToInt()
        val lastNumber = tcNo[10].digitToInt()
        val isFirst10NumberSumEqualLastNumber = (first10NumberSum % 10) == lastNumber
        val isRuleTrue = ((evenNumbersSum * 7 + oddNumbersSum * 9) % 10) == numberBeforeLastNumber
        val isEvenRuleTrue = (evenNumbersSum * 8) % 10 == lastNumber
        val rule = (evenNumbersSum * 7 - oddNumbersSum) % 10 == numberBeforeLastNumber
        val isLastNumberEven = lastNumber % 2 == 0

        return isFirst10NumberSumEqualLastNumber && isEvenRuleTrue && isRuleTrue && rule && isLastNumberEven
    }

}
fun String.isValidEmail(): Boolean {
    return RegexUtils.EMAIL_ADDRESS.matcher(this).matches()
}


fun String.isValidTCKN(): Boolean {
    return RegexUtils.isValidTCKN(this)
}