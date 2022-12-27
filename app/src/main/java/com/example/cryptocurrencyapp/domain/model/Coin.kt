package com.example.cryptocurrencyapp.domain.model

import com.example.cryptocurrencyapp.data.remote.dto.CoinDto

data class Coin(
    val id: String,
    val is_active: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String
)



