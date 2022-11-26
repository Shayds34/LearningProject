package com.sederikkuapplication.shared.data.repository.models.transaction

import com.sederikkuapplication.shared.data.network.models.Action
import com.sederikkuapplication.shared.data.network.models.AssetApiModel
import com.sederikkuapplication.shared.data.network.models.Operation
import com.sederikkuapplication.shared.data.network.models.ScamInfoApiModel

/**
 * Response model used by Proteo Repository
 */
data class TransactionRepositoryModel(
    val txHash: String,
    val gasLimit: Number?,
    val gasPrice: Number?,
    val gasUsed: Number?,
    val miniBlockHash: String?,
    val nonce: Number?,
    val receiver: String,
    val receiverAssets: AssetApiModel,
    val receiverShard: Number,
    val round: Number?,
    val sender: String,
    val senderAssets: AssetApiModel,
    val senderShard: Number,
    val signature: String?,
    val status: String,
    val value: String,
    val fee: String?,
    val timestamp: Number,
    val data: String?,
    val function: String?,
    val action: Action,
    val scamInfoApiModel: ScamInfoApiModel?,
    val type: String?, //
    val originalTxHash: String?,
    val prendingResults: Boolean?,
    val operations: List<Operation>?
)
