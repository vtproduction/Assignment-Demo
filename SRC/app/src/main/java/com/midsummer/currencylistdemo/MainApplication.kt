package com.midsummer.currencylistdemo

import android.app.Application
import com.midsummer.currencylistdemo.di.AppComponent
import com.midsummer.currencylistdemo.di.AppModule
import com.midsummer.currencylistdemo.di.DaggerAppComponent

/**
 * Created by nienle on 02,March,2022
 * Midsummer.
 * Ping me at nienbkict@gmail.com
 * Happy coding ^_^
 */
class MainApplication : Application() {

    lateinit var appComponent: AppComponent

    companion object {
        lateinit var instance : MainApplication
    }

    override fun onCreate() {

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
        super.onCreate()
        instance = this
    }


}

