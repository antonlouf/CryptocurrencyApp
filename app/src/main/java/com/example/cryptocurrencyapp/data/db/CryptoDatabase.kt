package com.example.cryptocurrencyapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cryptocurrencyapp.domain.model.Coin

@Database(
    entities = [Coin::class],
    version = 1
)
abstract class CryptoDatabase : RoomDatabase(){

    abstract fun getCryptoDao(): CryptoDao

        companion object {
            private var INSTANCE: CryptoDatabase? = null

            fun getInstance(context: Context): CryptoDatabase {
                if (INSTANCE == null) {
                    synchronized(this) {
                        INSTANCE =
                            Room.databaseBuilder(
                                context,
                                CryptoDatabase::class.java,
                                "crypto_database"
                            )
                                .build()
                    }
                }
                return INSTANCE!!
            }
        }

}