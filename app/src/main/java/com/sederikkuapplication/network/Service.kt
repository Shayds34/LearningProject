package com.sederikkuapplication.network

import com.sederikkuapplication.network.apiElrond.TokenAccount
import com.sederikkuapplication.network.apiElrond.TokenWithBalance
import com.sederikkuapplication.network.apiElrond.Transaction
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {

    @GET("tokens/{identifier}/accounts?size=10000")
    suspend fun getHolders(@Path("identifier") identifier: String): Response<List<TokenAccount>>

    @GET("tokens/{identifier}/accounts?size=10000&receiver=erd1qqqqqqqqqqqqqpgq3j97mjvu7vpn638ekcupcy4n0x6rdnleznyqn5faj9&status=success")
    suspend fun getStackers(@Path("identifier") identifier: String): Response<List<Transaction>>

    @GET("accounts/{address}/tokens")
    suspend fun getAccountTokens(@Path("address") address: String): Response<List<TokenWithBalance>>

    companion object {
        private var service: Service? = null
        fun getInstance(): Service {
            if (service == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.elrond.com/")
                    .client(OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                service = retrofit.create(Service::class.java)
            }
            return service!!
        }
    }

}