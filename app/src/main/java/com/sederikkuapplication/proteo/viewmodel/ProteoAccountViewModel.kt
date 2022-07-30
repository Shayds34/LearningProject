package com.sederikkuapplication.proteo.viewmodel

import androidx.lifecycle.*
import com.example.common.utils.asLiveData
import com.example.common.utils.offer
import com.sederikkuapplication.constantes.ElrondQuery
import com.sederikkuapplication.constantes.ProteoDefi
import com.sederikkuapplication.modules.DispatchersName
import com.sederikkuapplication.network.elrond.TokenWithBalance
import com.sederikkuapplication.network.elrond.transaction.Transaction
import com.sederikkuapplication.proteo.ProteoAccountActivity
import com.sederikkuapplication.proteo.fragments.AccountUiModel
import com.sederikkuapplication.proteo.mapper.AccountDetailedUiMapper
import com.sederikkuapplication.proteo.mapper.TokensUiMapper
import com.sederikkuapplication.proteo.mapper.TransactionsUiMapper
import com.sederikkuapplication.proteo.usecase.ProteoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named
import kotlin.math.pow

@HiltViewModel
class ProteoAccountViewModel @Inject constructor(
    private val useCase: ProteoUseCase,
    private val accountDetailedMapper: AccountDetailedUiMapper,
    private val transactionsMapper: TransactionsUiMapper,
    private val tokensMapper: TokensUiMapper,
    savedStateHandle: SavedStateHandle,
    @Named(DispatchersName.UI_VIEWMODEL) private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    val address = savedStateHandle.get<String>(ProteoAccountActivity.EXTRA_ADDRESS) ?: ""

    private val _viewState = MutableLiveData<AccountUiModel>()
    val viewState: LiveData<AccountUiModel> by lazy {
        initialize()
        _viewState.asLiveData()
    }

    private fun initialize() {
        viewModelScope.launch(dispatcher) {

            _viewState.offer(
                AccountUiModel(
                    state = AccountUiModel.State.Loading
                )
            )

            val accountDetailed =
                accountDetailedMapper.mapUseCaseToUi(useCase.getAccountDetails(address))

            val accountTokens = tokensMapper.mapUseCaseToUi(
                useCase.getAccountTokens(
                    address, ElrondQuery.MAX_SIZE
                )
            )
            val proteoWithBalance =
                accountTokens.find { it.identifier == ProteoDefi.PROTEO_IDENTIFIER }
            val sProteoWithBalance =
                accountTokens.find { it.identifier == ProteoDefi.SPROTEO_IDENTIFIER }

            val eliteTx = transactionsMapper.mapUseCaseToUi(
                useCase.getAccountTransactions(
                    address = address,
                    from = ElrondQuery.FROM_FIRST_INDEX,
                    size = ElrondQuery.MAX_SIZE,
                    receiver = ProteoDefi.ELITE_SM,
                    status = ElrondQuery.STATUS_SUCCESS,
                    order = ElrondQuery.ORDER_ASC,
                    withOperations = ElrondQuery.WITHOUT_OPERATIONS
                )
            )

            _viewState.offer(
                AccountUiModel(
                    state = AccountUiModel.State.Success(
                        accountDetailed = accountDetailed,
                        proteoInWallet = mapToBalance(proteoWithBalance),
                        sProteoInWallet = mapToBalance(sProteoWithBalance),
                        eliteBalance = eliteBalance(eliteTx),
                        egldInStaking = singleAssetStaked(address, ProteoDefi.EGLD_FARM_ELITE),
                        usdcInStaking = singleAssetStaked(address, ProteoDefi.USDC_FARM_ELITE)
                    )
                )
            )
        }
    }

    private fun mapToBalance(token: TokenWithBalance?): String {
        return if (token != null) {
            "%.2f".format(token.balance.toDouble() / 10.toDouble().pow(token.decimals.toDouble()))
        } else {
            "%.2f".format(0.toDouble())
        }
    }

    private fun eliteBalance(transactions: List<Transaction>): String {
        var eliteBalance = 0E18
        for (tx in transactions) {
            when (tx.function) {
                "deposit" -> eliteBalance += tx.action.arguments.transfers[0].value.toDouble()
                "unstake" -> eliteBalance = 0E18
            }
        }
        return "%.2f".format(eliteBalance / 10.toDouble().pow(18))
    }

    private suspend fun singleAssetStaked(address: String, pool: String): String {
        var egldAmount = 0E18
        var from = 0
        var decimals = 18
        if (pool == ProteoDefi.USDC_FARM_ELITE) {
            decimals = 6
        }
        while (from >= 0) {
            val transactions = transactionsMapper.mapUseCaseToUi(
                useCase.getAccountTransactions(
                    address = address,
                    from = from.toString(),
                    size = ElrondQuery.WITH_OPERATIONS_MAX_SIZE,
                    receiver = pool,
                    status = ElrondQuery.STATUS_SUCCESS,
                    order = ElrondQuery.ORDER_ASC,
                    withOperations = ElrondQuery.WITH_OPERATIONS
                )
            )
            for (tx in transactions) {
                when (tx.function) {
                    "stake_lp" -> {
                        egldAmount +=
                            if (pool == ProteoDefi.EGLD_FARM_ELITE) {
                                tx.value.toDouble()
                            } else {
                                tx.operations!![0].value.toDouble()
                            }
                    }
                    "withdraw" -> {
                        egldAmount -=
                            if (tx.operations?.get(0)?.action ?: "" == "transfer") {
                                tx.operations!![0].value.toDouble()
                            } else {
                                tx.operations!![1].value.toDouble()
                            }
                    }
                }
            }
            if (transactions.size < 50) {
                from = -1
            } else {
                from += 50
            }
        }
        return "%.2f".format(egldAmount / 10.toDouble().pow(decimals))

    }


}