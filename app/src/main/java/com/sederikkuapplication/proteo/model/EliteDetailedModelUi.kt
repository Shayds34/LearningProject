package com.sederikkuapplication.proteo.model



data class EliteDetailedModelUi constructor(
    val data: Data
) {

    data class Data (
        val address: String,
        val shorten: String, // By mapping
        val balance: String
    )
}