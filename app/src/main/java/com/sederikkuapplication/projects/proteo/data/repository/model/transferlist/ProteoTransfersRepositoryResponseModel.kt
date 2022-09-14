package com.sederikkuapplication.projects.proteo.data.repository.model.transferlist

import com.example.common.network.GenericFailureCauseRepositoryModel

/**
 * Response model used by Proteo Repository
 */
sealed class ProteoTransfersRepositoryResponseModel {

    /**
     * If it's success
     */
    data class Success(
        val transfers: List<TransfersRepositoryModel>
    ) : ProteoTransfersRepositoryResponseModel()

    /**
     * If it's failure
     */
    data class GenericFailure(
        val cause: GenericFailureCauseRepositoryModel
    ) : ProteoTransfersRepositoryResponseModel()
}