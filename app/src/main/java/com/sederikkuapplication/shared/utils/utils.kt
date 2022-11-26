package com.sederikkuapplication.shared.utils

import java.text.DecimalFormat
import kotlin.math.pow

    fun shortenErd(erd: String, start: Int = 6, end: Int = 4): String {
        val str1 = erd.subSequence(0, start)
        val str2 = erd.subSequence(erd.length - end, erd.length)
        return "$str1...$str2"
    }

    fun balanceWithDecimals(balance: String, decimals: Number) : String {
        val formatter = DecimalFormat("#,###.##")
        return formatter.format((balance.toDouble() / 10.toDouble().pow(decimals.toDouble())))
    }

    fun supplyFormat(supply: String?): String {
        val formatter = DecimalFormat("#,###.##")
        return formatter.format(supply?.toDouble())
    }