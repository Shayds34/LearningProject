package com.sederikkuapplication.shared.data.network.models

/**
 * SCAM INFO API MODEL
 *
 * - [type] : Enum : [ none, potentialScam, scam ]
 */
data class ScamInfoApiModel (
    val type: String,
    val info: String?
)