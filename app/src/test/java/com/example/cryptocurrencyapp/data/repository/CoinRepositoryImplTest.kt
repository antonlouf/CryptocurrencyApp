package com.example.cryptocurrencyapp.data.repository

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.cryptocurrencyapp.data.db.CryptoDao
import com.example.cryptocurrencyapp.data.db.CryptoDatabase
import com.example.cryptocurrencyapp.domain.model.Coin
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CoinRepositoryImplTest {

    private lateinit var dao: CryptoDao
    private lateinit var db: CryptoDatabase
    private lateinit var coin1: Coin
    private lateinit var coin2: Coin

    @Before
    fun setup() {
        dao = CryptoDatabase.getInstance(Application()).getCryptoDao()
        coin1 = Coin(1, "1", true, "bitcoin", 1, "btc")
        coin2 = Coin(2, "2", true, "ether", 1, "eth")
    }


    @Test
     fun insertTest() = runBlocking{
        dao.insert(coin1)
        val coinById = dao.findCoinByCId(1)
        assertEquals(coinById, CoreMatchers.equalTo(coin2))
    }
}