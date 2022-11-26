package com.sederikkuapplication.shared.data.repository.mapper

import com.example.common.network.GenericFailureCauseRepositoryModel
import com.example.common.network.NetworkResponse
import com.example.common.network.Response
import com.sederikkuapplication.shared.data.network.models.AssetApiModel
import com.sederikkuapplication.shared.data.network.models.RoleApiModel
import com.sederikkuapplication.shared.data.network.models.ScamInfoApiModel
import com.sederikkuapplication.shared.data.network.responsemodels.AccountDetailedApiResponseModel
import com.sederikkuapplication.shared.data.network.responsemodels.TokenDetailedApiResponseModel
import com.sederikkuapplication.shared.data.repository.models.accountdetailed.AccountDetailedRepositoryModel
import com.sederikkuapplication.shared.data.repository.models.accountdetailed.AccountDetailedRepositoryResponseModel
import com.sederikkuapplication.shared.data.repository.models.asset.AssetRepositoryModel
import com.sederikkuapplication.shared.data.repository.models.role.RoleRepositoryModel
import com.sederikkuapplication.shared.data.repository.models.scaminfo.ScamInfoRepositoryModel
import com.sederikkuapplication.shared.data.repository.models.tokendetailed.ListTokenDetailedRepositoryResponseModel
import com.sederikkuapplication.shared.data.repository.models.tokendetailed.TokenDetailedRepositoryModel
import javax.inject.Inject

class AccountsRepositoryMapper @Inject constructor() {

    /**
     * map ACCOUNT DETAILS from Api to Repository
     */
    fun mapApiToRepository(
        response: NetworkResponse<AccountDetailedApiResponseModel>
    ) : AccountDetailedRepositoryResponseModel {
        return when (response) {
            is Response.Success -> mapToSuccess(response.successValue)
            is Response.Failure -> {
                AccountDetailedRepositoryResponseModel.GenericFailure(
                    cause = GenericFailureCauseRepositoryModel.UNKNOWN(
                        sourceThrowable = Throwable(response.cause.localizedMessage)
                    )
                )
            }
        }
    }

    private fun mapToSuccess(
        apiModel: AccountDetailedApiResponseModel
    ) : AccountDetailedRepositoryResponseModel {
        return AccountDetailedRepositoryResponseModel.Success(
            accountDetailed = AccountDetailedRepositoryModel(
                address = apiModel.address,
                balance = apiModel.balance,
                nonce = apiModel.nonce,
                shard = apiModel.shard,
                assets = apiModel.assets,
                code = apiModel.code,
                codeHash = apiModel.codeHash,
                rootHash = apiModel.rootHash,
                txCount = apiModel.txCount,
                scrCount = apiModel.scrCount,
                username = apiModel.username,
                developerReward = apiModel.developerReward,
                ownerAddress = apiModel.ownerAddress,
                deployedAt = apiModel.deployedAt,
                isUpgradeable = apiModel.isUpgradeable,
                isReadable = apiModel.isReadable,
                isPayable = apiModel.isPayable,
                isPayableBySmartContract = apiModel.isPayableBySmartContract,
                scamInfo = apiModel.scamInfo?.let { mapApiToRepository(it) }
            )
        )
    }


    /**
     * map "SCAM INFO" from Api to Repository
     */
    private fun mapApiToRepository(
        apiModel: ScamInfoApiModel
    ) : ScamInfoRepositoryModel {
        return ScamInfoRepositoryModel(
            type = apiModel.type,
            info = apiModel.info
        )
    }

    /**
     * map ACCOUNT TOKENS LIST from Api to Repository
     */

    fun mapApiToRepository(
        response: NetworkResponse<List<TokenDetailedApiResponseModel>>
    ) : ListTokenDetailedRepositoryResponseModel {
        return when (response) {
            is Response.Success -> ListTokenDetailedRepositoryResponseModel.Success(
                tokens = mapToTokens(response.successValue)
            )
            is Response.Failure -> {
                ListTokenDetailedRepositoryResponseModel.GenericFailure(
                    cause = GenericFailureCauseRepositoryModel.UNKNOWN(
                        sourceThrowable = Throwable(response.cause.localizedMessage)
                    )
                )
            }
        }
    }

    private fun mapToTokens(
        apiModel: List<TokenDetailedApiResponseModel>
    ) : List<TokenDetailedRepositoryModel> {
        return apiModel.map {
            TokenDetailedRepositoryModel(
                identifier = it.identifier,
                name = it.name,
                ticker = it.ticker,
                owner = it.owner,
                minted = it.minted,
                burnt = it.burnt,
                initialMinted = it.initialMinted,
                decimals = it.decimals,
                isPaused = it.isPaused,
                assets = it.assets?.let { it1 -> mapToAssets(it1) },
                transactions = it.transactions,
                accounts = it.accounts,
                canUpgrade = it.canUpgrade,
                canMint = it.canMint,
                canBurn = it.canBurn,
                canChangeOwner = it.canChangeOwner,
                canPause = it.canPause,
                canFreeze = it.canFreeze,
                canWipe = it.canWipe,
                price = it.price,
                marketCap = it.marketCap,
                supply = it.supply,
                circulatingSupply = it.circulatingSupply,
                role = it.role?.let { it1 -> mapToRoles(it1) }
            )
        }
    }

    /**
     * map "ASSET" from Api to Repository
     */
    private fun mapToAssets(
        apiModel: AssetApiModel
    ) : AssetRepositoryModel {
        return AssetRepositoryModel(
            website = apiModel.website,
            description = apiModel.description,
            ledgerSignature = apiModel.ledgerSignature,
            status = apiModel.status,
            lockedAccounts = apiModel.lockedAccounts,
            extraTokens = apiModel.extraTokens,
            pngUrl = apiModel.pngUrl,
            svgUrl = apiModel.svgUrl
        )
    }

    /**
     * map "ROLE" from Api to Repository
     */
    private fun mapToRoles(
        apiModel: List<RoleApiModel>
    ) : List<RoleRepositoryModel> {
        return apiModel.map {
            RoleRepositoryModel(
                address = it.address,
                canLocalMint = it.canLocalMint,
                canLocalBurn = it.canLocalBurn,
                roles = it.roles
            )
        }
    }


}