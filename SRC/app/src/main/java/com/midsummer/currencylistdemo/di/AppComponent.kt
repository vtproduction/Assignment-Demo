package com.midsummer.currencylistdemo.di

import com.midsummer.currencylistdemo.data.CurrencyInfoRepository
import com.midsummer.currencylistdemo.ui.shared.currencyList.CurrencyListFragment
import com.midsummer.currencylistdemo.ui.shared.currencyList.CurrencyListViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by nienle on 04,March,2022
 * Midsummer.
 * Ping me at nienbkict@gmail.com
 * Happy coding ^_^
 */

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(repository: CurrencyInfoRepository)
    fun inject(currencyInfoFragment: CurrencyListFragment)
    fun inject(currencyListViewModel: CurrencyListViewModel)
}