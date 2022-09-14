package com.sederikkuapplication.projects.proteo.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sederikkuapplication.constantes.Elrond
import com.sederikkuapplication.constantes.ElrondQuery
import com.sederikkuapplication.constantes.ProteoDefi
import com.sederikkuapplication.modules.DispatchersName
import com.sederikkuapplication.network.elrond.transaction.Transaction
import com.sederikkuapplication.network.network.Stats
import com.sederikkuapplication.projects.proteo.ProteoAccountActivity
import com.sederikkuapplication.projects.proteo.mapper.StatsUiMapper
import com.sederikkuapplication.projects.proteo.mapper.TransactionsUiMapper
import com.sederikkuapplication.projects.proteo.model.ProteoUnstakeModelUi
import com.sederikkuapplication.projects.proteo.model.TransactionModelUi
import com.sederikkuapplication.projects.proteo.usecase.ProteoUseCase
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

    private lateinit var networkStats: Stats
    private var autostakingTransactions: List<Transaction> = emptyList()
    private var epochToDisplay: Int = 0

    var unstakeList = mutableStateOf<List<ProteoUnstakeModelUi>>(emptyList())

    fun initialize() {
        viewModelScope.launch(Dispatchers.IO) {

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

            val transactionsWithdraw: List<ProteoUnstakeModelUi> = autostakingTransactions.map {
                mapToProteoUnstakeModelUi(it)
            }

            unstakeList.value = filterWithEpoch(transactionsWithdraw, epoch).filter { it.transaction.function == "unstake" }
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

    private fun mapToProteoUnstakeModelUi(transaction: Transaction): ProteoUnstakeModelUi {
        return ProteoUnstakeModelUi(
            transaction = transaction,
            shortenAddress = mapErd(transaction.sender),
            epoch = mapToEpoch(transaction.timestamp.toLong()),
            sProteo = mapActionValue(transaction),
            isWithdraw = mapToWithdraw(transaction)
        )
    }

    private fun mapActionValue(transaction: Transaction): String {
        return if (transaction.function == "unstake") {
            "%.2f".format(transaction.action!!.arguments.transfers[0].value.toDouble() / 1E18)
        }else {
            "0.00"
        }
    }


    private fun mapToWithdraw(transaction: Transaction): Boolean {
        var withdraw = false
        for (element in autostakingTransactions) {
            if(
                element.timestamp.toInt() > transaction.timestamp.toInt() &&
                element.function =="withdraw" &&
                element.sender == transaction.sender
            ) {
                withdraw = true
            }
        }
        return withdraw
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

    private fun elrondEpoch(unixSeconds: Number): Int {
        return ((unixSeconds.toLong() - Elrond.EPOCH_ZERO) / 86400).toInt()
    }

    private fun todayEpoch(): Int {
        return elrondEpoch(System.currentTimeMillis() / 1000)
    }

    //private fun transacationsTotalValue(list: List<Transaction>): Double {
    //    var total = 0E18
    //    for (tx in list) {
    //        total += tx.action.arguments.transfers[0].value.toDouble()
    //    }
    //    return total / 1E18
    //}

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



    private fun filterWithEpoch(
        list: List<ProteoUnstakeModelUi>,
        epoch: Number
    ): List<ProteoUnstakeModelUi> {
        val newlist = mutableListOf<ProteoUnstakeModelUi>()
        for (unstake in list) {
            if (elrondEpoch(unstake.transaction.timestamp.toLong()) + ProteoDefi.AUTOSTAKING_UNBOUND_PERIOD == epoch.toInt()) {
                newlist.add(unstake)
            }
        }
        return newlist
    }

}