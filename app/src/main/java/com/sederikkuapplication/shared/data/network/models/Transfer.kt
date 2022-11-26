package com.sederikkuapplication.shared.data.network.models

data class Transfer(
    val type: String,
    val name: String,
    val ticker: String,
    val svgUrl: String,
    val token: String,
    val decimals: Number,
    val value: String,
)