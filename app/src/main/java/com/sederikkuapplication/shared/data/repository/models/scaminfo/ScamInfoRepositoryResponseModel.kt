package com.sederikkuapplication.shared.data.repository.models.scaminfo

import com.example.common.network.GenericFailureCauseRepositoryModel

sealed class ScamInfoRepositoryResponseModel {

    /**
     * If it's success
     */
    data class Success(
        val scamInfo: ScamInfoRepositoryModel
    ) : ScamInfoRepositoryResponseModel()

    /**
     * If it's failure
     */
    data class GenericFailure(
        val cause: GenericFailureCauseRepositoryModel
    ) : ScamInfoRepositoryResponseModel()

}
