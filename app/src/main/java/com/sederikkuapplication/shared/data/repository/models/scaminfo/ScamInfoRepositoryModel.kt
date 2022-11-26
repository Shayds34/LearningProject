package com.sederikkuapplication.shared.data.repository.models.scaminfo

/**
 * SCAM INFO REPOSITORY MODEL
 *
 * - [type] : Enum : [ none, potentialScam, scam ]
 */
data class ScamInfoRepositoryModel(
    val type: String,
    val info: String?
)