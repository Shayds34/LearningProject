package com.sederikkuapplication.network

class MainRepository constructor(
    private val service: Service
) {
    // Obtient les détails de tous les Jetons de la liste
    //suspend fun getTokens(token: String) = service.getTokens(token)

    //Obtient la liste des comptes qui possèdent ce jeton
    suspend fun getHolders(identifier: String, size: Int) = service.getHolders(identifier, size.toString())

    // Obtient la liste des transactions ayant intéragit avec le SM de stacking
    suspend fun  getStackers(identifier: String) = service.getStackers(identifier)

    // Obtient la liste des jetons (Objet TokenWithBalance) d'une adresse
    suspend fun getAccountTokens(address: String) = service.getAccountTokens(address)
}