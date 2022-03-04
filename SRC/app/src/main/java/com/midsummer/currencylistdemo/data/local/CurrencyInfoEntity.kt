package com.midsummer.currencylistdemo.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * Created by nienle on 02,March,2022
 * Midsummer.
 * Ping me at nienbkict@gmail.com
 * Happy coding ^_^
 */
@Entity(tableName = "currencyInfo")
data class CurrencyInfoEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String = "",

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "symbol")
    val symbol: String
) : Serializable
