package com.example.cryptocurrencyapp.di

import com.example.cryptocurrencyapp.common.Constrans
import com.example.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.example.cryptocurrencyapp.data.repository.CoinRepositoryImpl
import com.example.cryptocurrencyapp.domain.repository.CoinRepository
import com.google.android.datatransport.runtime.dagger.Module
import com.google.android.datatransport.runtime.dagger.Provides

//Dependency injection managed by Hilt
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class) //As long as application is active
object AppModule {

    @Provides
    @Singleton //only one instance of return possible
    fun providePaprikaApi(): CoinPaprikaApi{
        return Retrofit.Builder()
            .baseUrl(Constrans.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) //Serialize json data
            .build()
            .create(CoinPaprikaApi::class.java) // create API interface
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaApi): CoinRepository{
        return CoinRepositoryImpl(api)
    }
}