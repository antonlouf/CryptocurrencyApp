package com.example.cryptocurrencyapp.data.remote.dto

import androidx.room.Entity
import com.example.cryptocurrencyapp.domain.model.Coin

data class CoinDto(
    val id: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

//mapping coin dto to kotlin object
fun CoinDto.toCoin(): Coin {
    return Coin(
        CId = rank, //LOS OP! FOUT
        id = id,
        is_active = is_active,
        name = name,
        rank = rank,
        symbol = symbol
    )
}

