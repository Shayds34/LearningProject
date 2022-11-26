package com.sederikkuapplication.shared.data.repository.mapper

import android.util.Log
import com.example.common.network.GenericFailureCauseRepositoryModel
import com.example.common.network.NetworkResponse
import com.example.common.network.Response
import com.sederikkuapplication.shared.data.network.models.*
import com.sederikkuapplication.shared.data.network.responsemodels.TokenDetailedApiResponseModel
import com.sederikkuapplication.shared.data.network.responsemodels.TokenSupplyApiResponseModel
import com.sederikkuapplication.shared.data.network.responsemodels.TransactionApiResponseModel
import com.sederikkuapplication.shared.data.repository.models.asset.AssetRepositoryModel
import com.sederikkuapplication.shared.data.repository.models.lockedaccount.LockedAccountRepositoryModel
import com.sederikkuapplication.shared.data.repository.models.role.RoleRepositoryModel
import com.sederikkuapplication.shared.data.repository.models.tokendetailed.ListTokenDetailedRepositoryResponseModel
import com.sederikkuapplication.shared.data.repository.models.tokendetailed.TokenDetailedRepositoryModel
import com.sederikkuapplication.shared.data.repository.models.tokendetailed.TokenDetailedRepositoryResponseModel
import com.sederikkuapplication.shared.data.repository.models.tokensupply.TokenSupplyRepositoryResponseModel
import com.sederikkuapplication.shared.data.repository.models.tokensupply.TokenSupplyRepositoryModel
import com.sederikkuapplication.shared.data.repository.models.transaction.ListTransactionRepositoryResponseModel
import com.sederikkuapplication.shared.data.repository.models.transaction.TransactionRepositoryModel
import javax.inject.Inject

class TokensRepositoryMapper @Inject constructor() {

    /**
     * map TOKENS LIST from Api to Repository
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
                assets = it.assets?.let { it1 -> maptoAssets(it1) },
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
     * map TOKEN DETAILED from Api to Repository
     */
    fun mapApiToRepository(
        response: NetworkResponse<TokenDetailedApiResponseModel>
    ) : TokenDetailedRepositoryResponseModel {
        return when (response) {
            is Response.Success -> mapToSuccess(response.successValue)
            is Response.Failure -> {
                TokenDetailedRepositoryResponseModel.GenericFailure(
                    cause = GenericFailureCauseRepositoryModel.UNKNOWN(
                        sourceThrowable = Throwable(response.cause.localizedMessage)
                    )
                )
            }
        }
    }

    private fun mapToSuccess(
        apiModel: TokenDetailedApiResponseModel
    ) : TokenDetailedRepositoryResponseModel {
        return TokenDetailedRepositoryResponseModel.Success(
            tokenDetailed = TokenDetailedRepositoryModel(
                identifier = apiModel.identifier,
                name = apiModel.name,
                ticker = apiModel.ticker,
                owner = apiModel.owner,
                minted = apiModel.minted,
                burnt = apiModel.burnt,
                initialMinted = apiModel.initialMinted,
                decimals = apiModel.decimals,
                isPaused = apiModel.isPaused,
                assets = apiModel.assets?.let { maptoAssets(it) },
                transactions = apiModel.transactions,
                accounts = apiModel.accounts,
                canUpgrade = apiModel.canUpgrade,
                canMint = apiModel.canMint,
                canBurn = apiModel.canBurn,
                canChangeOwner = apiModel.canChangeOwner,
                canPause = apiModel.canPause,
                canFreeze = apiModel.canFreeze,
                canWipe = apiModel.canWipe,
                price = apiModel.price,
                marketCap = apiModel.marketCap,
                supply = apiModel.supply,
                circulatingSupply = apiModel.circulatingSupply,
                role = apiModel.role?.let { mapToRoles(it) }
            )
        )
    }

    /**
     * map "ASSET" from Api to Repository
     */
    private fun maptoAssets(
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


    /**
     * map TOKEN SUPPLY from Api to Repository
     */
    fun mapApiToRepository(
        response: NetworkResponse<TokenSupplyApiResponseModel>
    ) : TokenSupplyRepositoryResponseModel {
        return when (response) {
            is Response.Success -> mapToSuccess(response.successValue)
            is Response.Failure -> {
                TokenSupplyRepositoryResponseModel.GenericFailure(
                    cause = GenericFailureCauseRepositoryModel.UNKNOWN(
                        sourceThrowable = Throwable(response.cause.localizedMessage)
                    )
                )
            }
        }
    }

    private fun mapToSuccess(
        apiModel: TokenSupplyApiResponseModel
    ) : TokenSupplyRepositoryResponseModel {
        return TokenSupplyRepositoryResponseModel.Success(
            tokenSupply = TokenSupplyRepositoryModel(
                supply = apiModel.supply,
                circulatingSupply = apiModel.circulatingSupply,
                minted = apiModel.minted,
                burnt = apiModel.burnt,
                initialMinted = apiModel.initialMinted,
                lockedAccounts = mapToLockedAccounts(apiModel.lockedAccounts)
            )
        )
    }

    /**
     * map "LOCKED ACCOUNT" from Api to Repository
     */
    private fun mapToLockedAccounts(
        apiModel: List<LockedAccountApiModel>
    ) : List<LockedAccountRepositoryModel> {
        return apiModel.map {
            LockedAccountRepositoryModel(
                address = it.address,
                name = it.name,
                balance = it.balance
            )
        }
    }


    /**
     * map TOKEN TRANSACTIONS LIST (transafers) from Api to Repository
     */
    fun mapApiToRepository(
        response: NetworkResponse<List<TransactionApiResponseModel>>
    ) : ListTransactionRepositoryResponseModel {
        return when (response) {
            is Response.Success -> ListTransactionRepositoryResponseModel.Success(
                transactions = mapToTransactions(response.successValue)
            )
            is Response.Failure -> {
                ListTransactionRepositoryResponseModel.GenericFailure(
                    cause = GenericFailureCauseRepositoryModel.UNKNOWN(
                        sourceThrowable = Throwable(response.cause.localizedMessage)
                    )
                )
            }
        }
    }

    private fun mapToTransactions(
        apiModel: List<TransactionApiResponseModel>
    ) : List<TransactionRepositoryModel> {
        return apiModel.map {
            TransactionRepositoryModel(
                txHash = it.txHash,
                gasLimit = it.gasLimit,
                gasPrice = it.gasPrice,
                gasUsed = it.gasUsed,
                miniBlockHash = it.miniBlockHash,
                nonce = it.nonce,
                receiver = it.receiver,
                receiverAssets = it.receiverAssets,
                receiverShard = it.receiverShard,
                round = it.round,
                sender = it.sender,
                senderAssets = it.senderAssets,
                senderShard = it.senderShard,
                signature = it.signature,
                status = it.status,
                value = it.value,
                fee = it.fee,
                timestamp = it.timestamp,
                data = it.data,
                function = it.function,
                action = it.action,
                scamInfoApiModel = it.scamInfoApiModel,
                type = it.type,
                originalTxHash = it.originalTxHash,
                prendingResults = it.prendingResults,
                operations = it.operations
            )
        }
    }

}