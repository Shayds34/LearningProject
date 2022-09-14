package com.sederikkuapplication.projects.proteo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.utils.asLiveData
import com.example.common.utils.offer
import com.sederikkuapplication.constantes.ElrondQuery
import com.sederikkuapplication.constantes.ProteoDefi
import com.sederikkuapplication.modules.DispatchersName
import com.sederikkuapplication.projects.proteo.fragments.ProteoMainUiModel
import com.sederikkuapplication.projects.proteo.mapper.TokenUiMapper
import com.sederikkuapplication.projects.proteo.mapper.TransfersUiMapper
import com.sederikkuapplication.projects.proteo.usecase.ProteoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class ProteoMainViewModel @Inject constructor(
    private val useCase: ProteoUseCase,
    private val transfersUiMapper: TransfersUiMapper,
    private val tokenUiMapper: TokenUiMapper,
    @Named(DispatchersName.UI_VIEWMODEL) private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _viewState = MutableLiveData<ProteoMainUiModel>()
    val viewState: LiveData<ProteoMainUiModel> by lazy {
        initialize()
        _viewState.asLiveData()
    }

    private fun initialize() {
        viewModelScope.launch(dispatcher) {

            _viewState.offer(
                ProteoMainUiModel(
                    state = ProteoMainUiModel.State.Loading
                )
            )

            val proteo =
                tokenUiMapper.mapUseCaseToUi(
                    useCase.getTokenDetailed(
                        ProteoDefi.PROTEO_IDENTIFIER
                    )
                )

            val sproteo =
                tokenUiMapper.mapUseCaseToUi(
                    useCase.getTokenDetailed(
                        ProteoDefi.SPROTEO_IDENTIFIER
                    )
                )

            val farmRewards = transfersUiMapper.mapUseCaseToUi(
                useCase.getAccountTransfers(
                    address = ProteoDefi.FARM_REWARDS_SM,
                    from = ElrondQuery.FROM_FIRST_INDEX,
                    size = ElrondQuery.MAX_SIZE,
                    receiverOne = ProteoDefi.FARM_REWARDS_SM,
                    receiverTwo = ProteoDefi.AUTOSTAKING_SM,
                    status = ElrondQuery.STATUS_SUCCESS,
                    order = ElrondQuery.ORDER_DESC
                )
            )

            val callBack = farmRewards.find { it.function == "callBack" }
            val deposit = farmRewards.find { it.function == "deposit_from_farm_rewards" }

            val index =
                deposit!!.action!!.arguments.transfers[0].value.toDouble() / callBack!!.action!!.arguments.transfers[0].value.toDouble()


            _viewState.offer(
                ProteoMainUiModel(
                    state = ProteoMainUiModel.State.Success(
                        token = proteo,
                        proteoPrice = "%.2f".format(proteo.price?.toDouble()) + " $",
                        index = "%.5f".format(index)
                    )
                )
            )

        }
    }


}