package com.example.cryptocurrencyapp.data.db

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cryptocurrencyapp.domain.model.Coin

@Database(
    entities = [Coin::class],
    version = 1,
//    autoMigrations = [
//        AutoMigration(from = 1, to = 2 ),
//        AutoMigration(from = 2, to = 3, spec = CryptoDatabase.Migration2To3::cla)
//    ]
)
abstract class CryptoDatabase : RoomDatabase(){

    abstract fun getCryptoDao(): CryptoDao

        companion object {
            @Volatile
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
                                .fallbackToDestructiveMigration()
                                .build()
                    }
                }
                return INSTANCE!!
            }
        }

}