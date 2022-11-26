package com.sederikkuapplication.shared.data.repository.models.role

import com.example.common.network.GenericFailureCauseRepositoryModel

sealed class RoleRepositoryResponseModel {

    /**
     * If it's success
     */
    data class Success(
        val role: RoleRepositoryModel
    ) : RoleRepositoryResponseModel()

    /**
     * If it's failure
     */
    data class GenericFailure(
        val cause: GenericFailureCauseRepositoryModel
    ) : RoleRepositoryResponseModel()

}