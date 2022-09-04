package com.sederikkuapplication.proteo

import com.sederikkuapplication.proteo.data.api.ProteoApi
import com.sederikkuapplication.proteo.data.api.ProteoApiImpl
import com.sederikkuapplication.proteo.data.repository.ProteoRepository
import com.sederikkuapplication.proteo.data.repository.ProteoRepositoryImpl
import com.sederikkuapplication.proteo.data.service.ProteoNetwork
import com.sederikkuapplication.proteo.usecase.ProteoUseCase
import com.sederikkuapplication.proteo.usecase.ProteoUseCaseImpl
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
class ProteoRepositoryModule {

    companion object {
        const val PROTEO_BASE_URL = "https://api.elrond.com/"
    }

    /**
     * Binds instance
     */
    @ActivityRetainedScoped
    @Provides
    fun providesProteoApi(impl: ProteoApiImpl): ProteoApi = impl

    /**
     * Binds instance
     */
    @ActivityRetainedScoped
    @Provides
    fun providesProteoRepository(impl: ProteoRepositoryImpl): ProteoRepository = impl

    /**
     * Binds instance
     */
    @ActivityRetainedScoped
    @Provides
    fun providesProteoUseCase(impl: ProteoUseCaseImpl): ProteoUseCase = impl

    /**
     * Creates retrofit instance
     */
    @ActivityRetainedScoped
    @Provides
    fun providesProteoNetwork(): ProteoNetwork {
        return Retrofit.Builder()
            .baseUrl(PROTEO_BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProteoNetwork::class.java)
    }

}