package com.sederikkuapplication.proteo.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.utils.asLiveData
import com.example.common.utils.offer
import com.sederikkuapplication.constantes.ElrondQuery
import com.sederikkuapplication.constantes.ProteoDefi
import com.sederikkuapplication.modules.DispatchersName
import com.sederikkuapplication.network.proteoDefi.EliteDetailed
import com.sederikkuapplication.proteo.ProteoAccountActivity
import com.sederikkuapplication.proteo.fragments.EliteRankingUiModel
import com.sederikkuapplication.proteo.mapper.TransactionsUiMapper
import com.sederikkuapplication.proteo.model.EliteDetailedModelUi
import com.sederikkuapplication.proteo.usecase.ProteoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named
import kotlin.math.pow

@HiltViewModel
class ProteoEliteRankingViewModel @Inject constructor(
    private val useCase: ProteoUseCase,
    private val transactionsUiMapper: TransactionsUiMapper,
    @Named(DispatchersName.UI_VIEWMODEL) private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _viewState = MutableLiveData<EliteRankingUiModel>()
    val viewState: LiveData<EliteRankingUiModel> by lazy {
        initialize()
        _viewState.asLiveData()
    }

    private fun initialize() {
        viewModelScope.launch(dispatcher) {

            _viewState.offer(
                EliteRankingUiModel(
                    state = EliteRankingUiModel.State.Loading
                )
            )

            val ranking = mutableListOf<EliteDetailed>()
            val eliteTx = transactionsUiMapper.mapUseCaseToUi(
                useCase.getAccountTransactions(
                    address = ProteoDefi.ELITE_SM,
                    from = ElrondQuery.FROM_FIRST_INDEX,
                    size = ElrondQuery.MAX_SIZE,
                    receiver = ProteoDefi.ELITE_SM,
                    status = ElrondQuery.STATUS_SUCCESS,
                    order = ElrondQuery.ORDER_ASC,
                    withOperations = ElrondQuery.WITHOUT_OPERATIONS
                )
            )

            for (tx in eliteTx) {
                when (tx.function) {
                    "deposit" -> {
                        val find = ranking.find { it.address == tx.sender }
                        if (find == null) {
                            val elite = EliteDetailed(
                                tx.sender,
                                tx.action.arguments.transfers[0].value.toDouble()
                            )
                            ranking.add(elite)
                        } else {
                            find.sproteo =
                                (find.sproteo.toDouble() + tx.action.arguments.transfers[0].value.toDouble())
                        }
                    }
                    "unstake" -> {
                        ranking.find { it.address == tx.sender }!!.sproteo = 0
                    }
                    else -> {}
                }
            }
            ranking.sortByDescending { it.sproteo.toDouble() }

            _viewState.offer(
                EliteRankingUiModel(
                    state = EliteRankingUiModel.State.Success(
                        adapterItems = ranking.map { mapToEliteDetailedModelUi(it) }
                    )
                )
            )

        }
    }

    private fun mapToEliteDetailedModelUi(elite: EliteDetailed): EliteDetailedModelUi {
        return EliteDetailedModelUi(
            data = EliteDetailedModelUi.Data(
                address = elite.address,
                shorten = shortenErd(elite.address),
                balance = "%.2f".format(elite.sproteo.toDouble() / 10.toDouble().pow(18))

            )
        )
    }

    fun onCardClicked(eliteDetailedModelUi: EliteDetailedModelUi, context: Context) {
        val intent = Intent(context, ProteoAccountActivity::class.java)
        intent.putExtra("EXTRA_ADDRESS", eliteDetailedModelUi.data.address)
        context.startActivity(intent)
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

}