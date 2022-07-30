package com.sederikkuapplication.proteo.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.utils.asLiveData
import com.example.common.utils.offer
import com.sederikkuapplication.constantes.Elrond
import com.sederikkuapplication.constantes.ElrondQuery
import com.sederikkuapplication.constantes.ProteoDefi
import com.sederikkuapplication.modules.DispatchersName
import com.sederikkuapplication.network.elrond.transaction.Action
import com.sederikkuapplication.network.elrond.transaction.Transaction
import com.sederikkuapplication.network.network.Stats
import com.sederikkuapplication.proteo.ProteoAccountActivity
import com.sederikkuapplication.proteo.fragments.UiModel
import com.sederikkuapplication.proteo.mapper.StatsUiMapper
import com.sederikkuapplication.proteo.mapper.TransactionsUiMapper
import com.sederikkuapplication.proteo.model.TransactionModelUi
import com.sederikkuapplication.proteo.usecase.ProteoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Date
import java.text.SimpleDateFormat
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class ProteoUnstakingViewModel @Inject constructor(
    private val useCase: ProteoUseCase,
    private val statsMapper: StatsUiMapper,
    private val transactionsMapper: TransactionsUiMapper,
    @Named(DispatchersName.UI_VIEWMODEL) private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _viewState = MutableLiveData<UiModel>()
    val viewState: LiveData<UiModel> by lazy {
        initialize()
        _viewState.asLiveData()
    }

    private lateinit var networkStats: Stats
    private var autostakingTransactions: List<Transaction> = emptyList()
    private var epochToDisplay: Int = 0

    private fun initialize() {
        viewModelScope.launch(Dispatchers.IO) {

            _viewState.offer(
                UiModel(
                    state = UiModel.State.Loading
                )
            )

            networkStats = statsMapper.mapUseCaseToUi(useCase.getNetworkStats())
            autostakingTransactions =
                transactionsMapper.mapUseCaseToUi(
                    useCase.getAccountTransactions(
                        address = ProteoDefi.AUTOSTAKING_SM,
                        from = ElrondQuery.FROM_FIRST_INDEX,
                        size = ElrondQuery.MAX_SIZE,
                        receiver = ProteoDefi.AUTOSTAKING_SM,
                        status = ElrondQuery.STATUS_SUCCESS,
                        order = ElrondQuery.ORDER_ASC,
                        withOperations = ElrondQuery.WITHOUT_OPERATIONS
                    )
                )
            epochToDisplay = networkStats.epoch.toInt()

            displayEpochPage(epochToDisplay)
        }
    }

    private fun displayEpochPage(epoch: Int) {
        viewModelScope.launch(dispatcher) {
            val autostakingUnstakeTransactions: List<Transaction> =
                filterTransactionsListWithFunction(autostakingTransactions, "unstake", epoch)

            val totalValueTransactions = transacationsTotalValue(autostakingUnstakeTransactions)

            _viewState.offer(
                UiModel(
                    state = UiModel.State.Success(
                        adapterItems = autostakingUnstakeTransactions.map {
                            mapToTransactionModelUi(
                                it
                            )
                        },
                        dailyCount = "%.2f".format(totalValueTransactions),
                        epochOnDisplay = epoch.toString()
                    )
                )
            )
        }
    }

    fun onPreviousClick() {
        epochToDisplay -= 1
        displayEpochPage(epochToDisplay)
    }

    fun onNextClick() {
        epochToDisplay += 1
        displayEpochPage(epochToDisplay)
    }

    private fun mapToTransactionModelUi(transaction: Transaction): TransactionModelUi {
        return TransactionModelUi(
            data = TransactionModelUi.Data(
                sender = transaction.sender,
                action = transaction.action,
                timestamp = transaction.timestamp,
                epoch = mapToEpoch(transaction.timestamp.toLong()),
                function = transaction.function,
                token = mapActionValue(transaction.action),
                shortenErd = mapErd(transaction.sender)
            )
        )
    }

    fun onCardClicked(transactionModelUi: TransactionModelUi, context: Context) {
        context.startActivity(
            ProteoAccountActivity.newInstance(
                context,
                transactionModelUi.data.sender
            )
        )
    }

    private fun toDate(unixSeconds: Int): String {
        val pattern = SimpleDateFormat("dd MMMM yyyy - HH:mm")
        val date = Date(unixSeconds.toLong() * 1000)
        return pattern.format(date)
    }

    private fun elrondEpoch(unixSeconds: Long): Int {
        return ((unixSeconds - Elrond.EPOCH_ZERO) / 86400).toInt()
    }

    private fun todayEpoch(): Int {
        return elrondEpoch(System.currentTimeMillis() / 1000)
    }

    private fun transacationsTotalValue(list: List<Transaction>): Double {
        var total = 0E18
        for (tx in list) {
            total += tx.action.arguments.transfers[0].value.toDouble()
        }
        return total / 1E18
    }

    /**
     * Reformat an [erd] address that is usually too long
     * /!\ For display only purpose
     * @param[erd] The erd address to shorten
     * @param[start] Optional: The lenght of the initial part / default : 6
     * @param[end] Optional: the length of the end part / default : 4
     * @return the new format in a String
     */
    private fun shortenErd(erd: String, start: Int = 6, end: Int = 4): String {
        val str1 = erd.subSequence(0, start)
        val str2 = erd.subSequence(erd.length - end, erd.length)
        return "$str1...$str2"
    }

    private fun mapErd(erd: String, start: Int = 6, end: Int = 4) = shortenErd(erd, start, end)

    private fun mapToEpoch(timestamp: Long) = elrondEpoch(timestamp).toString()

    private fun mapActionValue(action: Action): String {
        return "%.2f".format(action.arguments.transfers[0].value.toDouble() / 1E18)
    }

    private fun filterTransactionsListWithFunction(
        list: List<Transaction>,
        function: String,
        epoch: Number
    ): List<Transaction> {
        val newlist = mutableListOf<Transaction>()
        for (tx in list) {
            if ((tx.function == function) && elrondEpoch(tx.timestamp.toLong()) + ProteoDefi.AUTOSTAKING_UNBOUND_PERIOD == epoch.toInt()) {
                newlist.add(tx)
            }
        }
        return newlist
    }

}