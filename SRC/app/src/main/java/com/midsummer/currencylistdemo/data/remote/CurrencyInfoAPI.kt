package com.midsummer.currencylistdemo.data.remote

import com.midsummer.currencylistdemo.data.local.CurrencyInfoEntity
import com.midsummer.currencylistdemo.pojo.CurrencyInfo
import retrofit2.http.GET

/**
 * Created by nienle on 02,March,2022
 * Midsummer.
 * Ping me at nienbkict@gmail.com
 * Happy coding ^_^
 */
interface CurrencyInfoAPI {

    @GET("vtproduction/Assignment-Demo/main/data.json?token=GHSAT0AAAAAABR4M5WHCWOZVYXG6MQJTW3CYQ6YDNA")
    suspend fun getCurrenciesInfo() : List<CurrencyInfo>
}