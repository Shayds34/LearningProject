package com.sederikkuapplication.shared.data.repository

import com.sederikkuapplication.shared.data.network.ElrondApi
import com.sederikkuapplication.shared.data.network.ElrondApiImpl
import com.sederikkuapplication.shared.data.network.ElrondNetwork
import com.sederikkuapplication.shared.Constants
import com.sederikkuapplication.shared.data.repository.implementation.AccountsRepositoryImpl
import com.sederikkuapplication.shared.data.repository.implementation.NetworkRepositoryImpl
import com.sederikkuapplication.shared.data.repository.implementation.TokensRepositoryImpl
import com.sederikkuapplication.shared.domain.repository.AccountsRepository
import com.sederikkuapplication.shared.domain.repository.NetworkRepository
import com.sederikkuapplication.shared.domain.repository.TokensRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Dagger module for proteo repository
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
class RepositoryModule {

    /**
     * Binds instance
     */
    @ActivityRetainedScoped
    @Provides
    fun providesElrondApi(impl: ElrondApiImpl) : ElrondApi = impl

    /**
     * Binds instance
     */
    @ActivityRetainedScoped
    @Provides
    fun providesAccountsRepository(impl: AccountsRepositoryImpl) : AccountsRepository = impl

    /**
     * Binds instance
     */
    @ActivityRetainedScoped
    @Provides
    fun providesNetworkRepository(impl: NetworkRepositoryImpl) : NetworkRepository = impl

    /**
     * Binds instance
     */
    @ActivityRetainedScoped
    @Provides
    fun providesTokensRepository(Impl: TokensRepositoryImpl) : TokensRepository = Impl

    /**
     * Creates retrofit instance
     */
    @ActivityRetainedScoped
    @Provides
    fun provideElrondNetwork() : ElrondNetwork {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ElrondNetwork::class.java)
    }

}