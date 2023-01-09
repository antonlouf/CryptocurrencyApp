package com.example.cryptocurrencyapp.presentation

//Navigation
sealed class Screen(val route: String){
    object CoinListScreen: Screen("coin_list_screen")
    object CoinDetailScreen: Screen("coin_detail_screen")
    object SettingsScreen: Screen("settings_screen")
}
