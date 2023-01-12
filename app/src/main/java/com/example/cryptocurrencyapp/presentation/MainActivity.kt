package com.example.cryptocurrencyapp.presentation

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrencyapp.data.db.CryptoDao
import com.example.cryptocurrencyapp.data.db.CryptoDatabase
import com.example.cryptocurrencyapp.di.AppModule
import com.example.cryptocurrencyapp.presentation.navItems.BottomNavItem
import com.example.cryptocurrencyapp.presentation.coin_detail.CoinDetailScreen
import com.example.cryptocurrencyapp.presentation.coin_list.CoinListScreen
import com.example.cryptocurrencyapp.presentation.settings.SettingsScreen
import com.example.cryptocurrencyapp.presentation.theme.CryptocurrencyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint //allows dagger&hilt to inject dependencies
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptocurrencyAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    Scaffold(bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    "Home",
                                    Screen.CoinListScreen.route,
                                    Icons.Default.Home
                                ),
                                BottomNavItem(
                                    "Detail",
                                    Screen.CoinDetailScreen.route + "/{coinId}",
                                    Icons.Default.Info
                                ),
                                BottomNavItem(
                                    "Settings",
                                    Screen.SettingsScreen.route,
                                    Icons.Default.Settings
                                )
                            ),
                            navController = navController,
                            onItemClick = { navController.navigate(it.route) }
                        )
                    }
                    ) {
                        navigation(navController = navController)
                    }
                }
            }
        }
    }

    @Composable
    fun navigation(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = Screen.CoinListScreen.route
        ) {
            composable(
                route = Screen.CoinListScreen.route
            ) {
                CoinListScreen(navController)
            }
            composable(
                route = Screen.CoinDetailScreen.route + "/{coinId}",
            ) {
                CoinDetailScreen()
            }
            composable(
                route = Screen.SettingsScreen.route
            ) {
                SettingsScreen()
            }
        }
    }


    @Composable
    fun BottomNavigationBar(
        items: List<BottomNavItem>,
        navController: NavController,
        modifier: Modifier = Modifier,
        onItemClick: (BottomNavItem) -> Unit //pass through which item is clicked
    ) {
        val backStackEntry = navController.currentBackStackEntryAsState()
        BottomNavigation(
            //looks
        ) {
            items.forEach { item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                BottomNavigationItem(
                    selected = selected,
                    onClick = { onItemClick(item) },
                    selectedContentColor = Color.Green,
                    unselectedContentColor = Color.Gray,
                    icon = {
                        Icon(imageVector =  item.icon, contentDescription = item.name)
                    }
                )
            }
        }
    }
}


