package com.example.cryptocurrencyapp.data.repository

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.cryptocurrencyapp.data.db.CryptoDao
import com.example.cryptocurrencyapp.data.db.CryptoDatabase
import com.example.cryptocurrencyapp.domain.model.Coin
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class CoinRepositoryImplTest  {

    private lateinit var dao: CryptoDao
    private lateinit var db: CryptoDatabase
    private lateinit var coin1: Coin
    private lateinit var coin2: Coin

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, CryptoDatabase::class.java).build()
        dao = db.getCryptoDao()

        coin1 = Coin(1, "1", true, "bitcoin", 1, "btc")
        coin2 = Coin(2, "2", true, "ether", 1, "eth")
    }

    @After
    fun closeDB(){
        db.close()
    }

    @Test
    fun insertTest() = runBlocking{
        dao.insert(coin1)
        val coinById = dao.findCoinByCId(1)
        assertEquals(coinById, coin2)
    }
}
