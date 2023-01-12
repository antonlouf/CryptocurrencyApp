package com.example.cryptocurrencyapp.data.repository


import com.example.cryptocurrencyapp.data.db.CryptoDao
import com.example.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.example.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.example.cryptocurrencyapp.data.remote.dto.toCoin
import com.example.cryptocurrencyapp.domain.model.Coin
import com.example.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


import javax.inject.Inject

//Misschien het checken van
class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi,
    private var dao: CryptoDao
) : CoinRepository {

    val ioScope = CoroutineScope(Dispatchers.IO)
    var coins: List<Coin> = emptyList()

    //find out if database is full?
    //then decide on what on to import from api
    //or to take from database

    override suspend fun getCoins(): List<Coin> {
        ioScope.launch {
            coins = dao.getAllCoins()
        }

        if (coins.isEmpty()) {
            coins = getCoinsByApi()
        }

        ioScope.launch { coins.forEach { dao.insert(it) } }

        return coins
    }

    override suspend fun getCoinsByApi(): List<Coin> {
        var i: Int = 0
        coins = api.getCoins().map { it.toCoin(i++) }
        return coins
    }


    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }


}