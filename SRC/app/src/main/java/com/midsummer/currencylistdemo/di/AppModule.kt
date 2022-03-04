package com.midsummer.currencylistdemo.di

import android.content.Context
import com.midsummer.currencylistdemo.data.CurrencyInfoRepository
import com.midsummer.currencylistdemo.data.local.CurrencyInfoDAO
import com.midsummer.currencylistdemo.data.local.CurrencyInfoDatabase
import com.midsummer.currencylistdemo.data.remote.CurrencyInfoAPI
import com.midsummer.currencylistdemo.data.remote.CurrencyInfoAPIService
import com.midsummer.currencylistdemo.ui.shared.currencyList.CurrencyListViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by nienle on 04,March,2022
 * Midsummer.
 * Ping me at nienbkict@gmail.com
 * Happy coding ^_^
 */
@Module
class AppModule(val context: Context) {


    @Singleton
    @Provides
    fun provideCurrencyInfoApi() : CurrencyInfoAPI =
        CurrencyInfoAPIService.getClient()

    @Singleton
    @Provides
    fun provideLocalDatabase() : CurrencyInfoDAO =
        CurrencyInfoDatabase.invoke(context).currencyInfoDAO()

    @Provides
    fun provideRepository() =
        CurrencyInfoRepository()


}