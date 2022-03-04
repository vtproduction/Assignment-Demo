package com.midsummer.currencylistdemo.data

import com.midsummer.currencylistdemo.MainApplication
import com.midsummer.currencylistdemo.data.local.CurrencyInfoDAO
import com.midsummer.currencylistdemo.data.local.toCurrencyInfoEntityList
import com.midsummer.currencylistdemo.data.local.toCurrencyInfoList
import com.midsummer.currencylistdemo.data.remote.CurrencyInfoAPI
import com.midsummer.currencylistdemo.pojo.CurrencyInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by nienle on 03,March,2022
 * Midsummer.
 * Ping me at nienbkict@gmail.com
 * Happy coding ^_^
 */
class CurrencyInfoRepository {

    @Inject lateinit var  remote: CurrencyInfoAPI
    @Inject lateinit var local: CurrencyInfoDAO

    init {
        MainApplication.instance.appComponent.inject(this)
    }

    suspend fun fetchDataFromRemote(){
        withContext(Dispatchers.IO){
            try{
                val currencyList = remote.getCurrenciesInfo()
                local.insertCurrencies(currencyList.toCurrencyInfoEntityList())
            }catch(t: Throwable){
                throw t
            }
        }
    }

    suspend fun fetchDataFromLocal() : List<CurrencyInfo>{
        try {
            return local.getCurrencies().toCurrencyInfoList()
        } catch (t: Throwable) {
            throw t
        }
    }
}