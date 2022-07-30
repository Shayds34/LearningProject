package com.sederikkuapplication.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sederikkuapplication.network.MainRepository
import com.sederikkuapplication.network.Tokens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FirstViewModel constructor(
    private val repository: MainRepository
) : ViewModel() {

    private var job: Job? = null

    val tokensList = MutableLiveData<List<Tokens>>()

    suspend fun onClick() {
        job = CoroutineScope(Dispatchers.IO).launch {
            // Appel au WS (WebService)
            val response = repository.getTokens()

            // Post la valeur reçue pour qu'elle soit observée par le Fragment
            tokensList.postValue(response.body())
        }
    }
}