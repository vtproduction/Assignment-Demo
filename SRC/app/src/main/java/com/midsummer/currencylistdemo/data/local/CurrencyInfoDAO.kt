package com.midsummer.currencylistdemo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by nienle on 02,March,2022
 * Midsummer.
 * Ping me at nienbkict@gmail.com
 * Happy coding ^_^
 */
@Dao
interface CurrencyInfoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrencies(data: List<CurrencyInfoEntity>)

    @Query("SELECT * from currencyInfo")
    suspend fun getCurrencies() : List<CurrencyInfoEntity>
}