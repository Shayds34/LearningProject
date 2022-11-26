package com.sederikkuapplication.shared.data.repository.models.accountdetailed

import com.sederikkuapplication.shared.data.repository.models.scaminfo.ScamInfoRepositoryModel

/**
 * ACCOUNT DETAILED REPOSITORY MODEL
 *
 * - [address] : Account bech32 address
 * - [balance] : Balance
 * - [nonce] : Account current nonce
 * - [shard] : The shard ID allocated to the account
 * - [assets] : !!! To review & test !!!
 * - [code] : The source code in hex format
 * - [codeHash] : The hash of the source code
 * - [rootHash] : The hash of the root node
 * - [txCount] : The number of transactions performed on this account
 * - [scrCount] : The number of smart contract results of this account
 * - [username] : The username specific for this account
 * - [developerReward] : The developer reward
 * - [ownerAddress] : The address in bech 32 format of owner account
 * - [deployedAt] : Specific property flag for smart contract
 * - [isUpgradeable] : Specific property flag for smart contract
 * - [isPayable] : Specific property flag for smart contract
 * - [isPayableBySmartContract] : Specific property flag for smart contract
 * - [scamInfo] : Scam informations
 */
data class AccountDetailedRepositoryModel(
    val address: String,
    val balance: String,
    val nonce: Number,
    val shard: Number,
    val assets: List<String>?,
    val code: String?,
    val codeHash: String,
    val rootHash: String,
    val txCount: Number,
    val scrCount: Number,
    val username: String,
    val developerReward: String,
    val ownerAddress: String,
    val deployedAt: Number,
    val isUpgradeable: Boolean,
    val isReadable: Boolean,
    val isPayable: Boolean,
    val isPayableBySmartContract: Boolean?,
    val scamInfo: ScamInfoRepositoryModel?,
)
