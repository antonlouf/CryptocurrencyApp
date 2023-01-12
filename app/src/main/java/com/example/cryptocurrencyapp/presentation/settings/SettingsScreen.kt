package com.example.cryptocurrencyapp.presentation.settings

import android.app.Application
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.cryptocurrencyapp.data.db.CryptoDao
import com.example.cryptocurrencyapp.data.db.CryptoDatabase
import com.example.cryptocurrencyapp.presentation.Screen
import com.example.cryptocurrencyapp.presentation.coin_list.components.CoinListItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@Composable
fun SettingsScreen(){
    val ioScope = CoroutineScope(Dispatchers.Default)
    val instance : CryptoDatabase = CryptoDatabase.getInstance(Application())
    val dao : CryptoDao = instance.getCryptoDao()
    
    Box(){
        //prutsen om navigatie van detail werkende te krijgen
            Button(onClick = { ioScope.launch { dao.deleteAllCoins() }}) {

            }
    }
}