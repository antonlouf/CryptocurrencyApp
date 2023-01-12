package com.example.cryptocurrencyapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "coins"
)
data class Coin(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cId", defaultValue = "0")
    val cId: Int,
    val id: String,
    val is_active: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String
)





