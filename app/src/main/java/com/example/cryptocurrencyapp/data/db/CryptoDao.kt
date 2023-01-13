package com.example.cryptocurrencyapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cryptocurrencyapp.domain.model.Coin

@Dao
interface CryptoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(coin: Coin): Long

    @Query("SELECT * FROM coins")
    fun getAllCoins(): List<Coin>

    @Query("SELECT * FROM coins WHERE cId = :cId")
    fun findCoinByCId(cId:Int): Coin

    @Query("DELETE FROM COINS")
    suspend fun deleteAllCoins()
}