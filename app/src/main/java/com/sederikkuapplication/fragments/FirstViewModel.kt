package com.sederikkuapplication.fragments

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sederikkuapplication.network.MainRepository
import com.sederikkuapplication.network.proteoDefi.ProteoAccount
import com.sederikkuapplication.projects.ProteoDefi
import kotlinx.coroutines.*

class FirstViewModel constructor(
    private val repository: MainRepository
) : ViewModel() {

    private var job: Job? = null

    suspend fun onClick() {
        job = CoroutineScope(Dispatchers.IO).launch {

            // Appel au WS (WebService)

            // Liste des Tokens natifs du projet
            val proteoTokens: List<String> = listOf(
                ProteoDefi.PROTEO_IDENTIFIER,
                ProteoDefi.SPROTEO_IDENTIFIER)

            // Liste des adresses utilisant le Projet
            val projectAddresses = mutableListOf<String>()

            // On récupère les addresses des Holders
            for (token in proteoTokens) {
                val response = repository.getHolders(token)
                // et on les stocke
                response.body()?.map { it ->
                    if (!projectAddresses.contains(it.address)) {
                        projectAddresses.add(it.address)
                    }
                }
            }
            // On récupère les addresses des Syackers
            val response = repository.getStackers(ProteoDefi.SPROTEO_IDENTIFIER)
            // Et on le stocke
            response.body()?.map { it ->
                if (!projectAddresses.contains(it.sender)) {
                    projectAddresses.add(it.sender)
                }
            }

            // On crée la liste qui va recolter tous les objets personnalisés
            val proteoAccounts = mutableListOf<ProteoAccount>()

            // Et on va chercher les données
            withContext(Dispatchers.Main) {
                for (address in projectAddresses){
                    val res = repository.getAccountTokens(address)
                    res.body()?.map{ it ->
                        if(it.identifier === ProteoDefi.PROTEO_IDENTIFIER){
                            val acc = ProteoAccount(
                                address = address,
                                proteoToken = it,
                            )
                            proteoAccounts.add(acc)
                        }
                    }
                }
            }


            for (account in proteoAccounts){
                Log.d("accounts", "${account.address} - ${account.proteoToken.balance}")
            }
            Log.d("Size", "${proteoAccounts.size}")

        }
    }
}