package com.example.cryptocurrencyapp.di

import android.app.Application
import android.content.Context
import com.example.cryptocurrencyapp.CoinApplication
import com.example.cryptocurrencyapp.common.Constants
import com.example.cryptocurrencyapp.data.db.CryptoDao
import com.example.cryptocurrencyapp.data.db.CryptoDatabase
import com.example.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.example.cryptocurrencyapp.data.repository.CoinRepositoryImpl
import com.example.cryptocurrencyapp.domain.repository.CoinRepository
import com.example.cryptocurrencyapp.presentation.MainActivity

//Dependency injection managed by Hilt
import dagger.hilt.InstallIn
import dagger.Module
import dagger.Provides
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
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) //Serialize Gson data
            .build()
            .create(CoinPaprikaApi::class.java) // create API interface
    }

    //Make DB instance and provide DAO
    @Provides
    @Singleton
    fun provideCryptoDAO(context: Application): CryptoDao{
        return CryptoDatabase.getInstance(context).getCryptoDao()
    }


    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaApi, dao: CryptoDao): CoinRepository{
        return CoinRepositoryImpl(api, dao)
    }
}