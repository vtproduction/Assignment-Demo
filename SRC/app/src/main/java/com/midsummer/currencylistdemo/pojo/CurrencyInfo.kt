package com.midsummer.currencylistdemo.pojo

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * Created by nienle on 02,March,2022
 * Midsummer.
 * Ping me at nienbkict@gmail.com
 * Happy coding ^_^
 */
data class CurrencyInfo(

    var id: String = "",
    val name: String = "",
    val symbol: String = ""
) : Serializable
