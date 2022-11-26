package com.sederikkuapplication.features.home.domain.models

data class Token(
    val identifier: String,
    val name: String,
    val ticker: String,
    val pngUrl: String?,
)