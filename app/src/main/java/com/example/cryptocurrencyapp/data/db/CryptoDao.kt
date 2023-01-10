package com.example.cryptocurrencyapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cryptocurrencyapp.domain.model.Coin

@Dao
interface CryptoDao {


    @Insert()
    suspend fun insert(coin: Coin): Long

    @Query("SELECT * FROM coins")
    fun getAllCoins(): List<Coin> // Livedata or stateflow
}