package com.example.cryptocurrencyapp.domain.repository

import com.example.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.example.cryptocurrencyapp.domain.model.Coin

interface CoinRepository {

    suspend fun getCoinsByApi(): List<Coin>

    suspend fun getCoinById(coinId: String): CoinDetailDto

    suspend fun getCoins(): List<Coin>
}