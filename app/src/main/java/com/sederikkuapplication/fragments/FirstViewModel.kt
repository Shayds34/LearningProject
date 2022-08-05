package com.sederikkuapplication.fragments

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.sederikkuapplication.R
import com.sederikkuapplication.network.MainRepository
import com.sederikkuapplication.network.apiElrond.TokenAccount
import com.sederikkuapplication.network.proteoDefi.ProteoAccount
import com.sederikkuapplication.projects.ProteoDefi
import kotlinx.coroutines.*

class FirstViewModel constructor(
    private val repository: MainRepository
) : ViewModel() {

    private var job: Job? = null

    suspend fun onClick() {
        job = CoroutineScope(Dispatchers.IO).launch {


            // Proteo, sproteo, holders

            val proteoHolders = mutableListOf<TokenAccount>()

            val unusedLp = listOf<String>(
                "erd1j35wnhst63s44xt9hpjf3fjkf8ptav6lzgvajcnecrlssmaw830q4zcpl3",
                "erd1qqqqqqqqqqqqqpgq6hzck3wac3ljmth7dkzk2wcw3c9lvcauznyq268sn6",
                "erd1qqqqqqqqqqqqqpgq86drapw9lr2y7vnzwcktc3hvlrev6dugznyqvc6flj"
            )

            val response = repository.getHolders(ProteoDefi.PROTEOEGLDLP_IDENTIFIER, 10000)
            response.body()?.map { it ->
                if(!proteoHolders.contains(it) && !unusedLp.contains(it.address)){
                        proteoHolders.add(it)
                    }
            }

            var totalamount: Double = 0E18
            for (holder in proteoHolders) totalamount += holder.balance.toDouble()



            // ICI
            // J'ai effacé
            //textView.text = (totalamount / 1E18).toString()



            Log.d("Size", "${totalamount / 1E18}")




            // Appel au WS (WebService)

            // Liste des Tokens natifs du projet
            //val proteoTokens: List<String> = listOf(
            //    ProteoDefi.PROTEO_IDENTIFIER,
            //    ProteoDefi.SPROTEO_IDENTIFIER)

            // Liste des adresses utilisant le Projet
            //val projectAddresses = mutableListOf<String>()

            // On récupère les addresses des Holders
            //for (token in proteoTokens) {
            //    val response = repository.getHolders(token)
                // et on les stocke
            //    response.body()?.map { it ->
            //        if (!projectAddresses.contains(it.address)) {
            //            projectAddresses.add(it.address)
            //        }
             //   }
            //}



            // On récupère les addresses des Stackers
            //val response = repository.getStackers(ProteoDefi.SPROTEO_IDENTIFIER)
            // Et on le stocke
            //response.body()?.map { it ->
                //if (!projectAddresses.contains(it.sender) && it.sender!= null) {
                    //projectAddresses.add(it.sender)
                //}
            //}





            // On crée la liste qui va recolter tous les objets personnalisés
            //val proteoAccounts = mutableListOf<ProteoAccount>()

            // Et on va chercher les données

                //for (address in projectAddresses){
                    //val res = repository.getAccountTokens(address)

                    //res.body()?.map{ it ->

                        //if(it.identifier == ProteoDefi.PROTEO_IDENTIFIER){
                            //Log.d("Alors:", "${address}")
                            //val acc = ProteoAccount(
                                //address = address,
                                //proteoToken = it,
                            //)
                            //proteoAccounts.add(acc)
                        //}
                   // }
               // }


            //for (account in proteoAccounts){
             //   Log.d("accounts", "${account.address} - ${account.proteoToken.balance}")
            //}
            //Log.d("Size", "${proteoAccounts.size}")

        }
    }
}