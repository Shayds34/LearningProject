package com.sederikkuapplication.projects.cyber

import androidx.lifecycle.ViewModel
import com.sederikkuapplication.projects.proteo.mapper.TokenUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CyberMainViewModel @Inject constructor(
   //private val useCase: CyberUseCase,
   private val tokenUiMapper: TokenUiMapper,
) : ViewModel() {



}
