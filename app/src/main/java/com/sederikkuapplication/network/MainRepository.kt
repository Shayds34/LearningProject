package com.sederikkuapplication.network

class MainRepository constructor(
    private val service: Service
) {
    suspend fun getTokens() = service.getTokens()
}