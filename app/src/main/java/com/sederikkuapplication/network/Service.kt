package com.sederikkuapplication.network

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Service {

    @GET("tokens/PROTEO-0c7311/accounts")
    suspend fun getTokens(): Response<List<Tokens>>

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