package com.example.cryptocurrencyapp.data.remote

import com.example.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.example.cryptocurrencyapp.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {
    //Get all coins
    @GET("/v1/coins")
    suspend fun getCoins() : List<CoinDto>

    //Get detailed coin
    @GET("v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String) : CoinDetailDto
}