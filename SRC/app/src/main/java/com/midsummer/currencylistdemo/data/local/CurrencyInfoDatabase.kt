package com.midsummer.currencylistdemo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.midsummer.currencylistdemo.internal.DATABASE_NAME

/**
 * Created by nienle on 02,March,2022
 * Midsummer.
 * Ping me at nienbkict@gmail.com
 * Happy coding ^_^
 */
@Database(entities = [CurrencyInfoEntity::class], version = 1)
abstract class CurrencyInfoDatabase : RoomDatabase(){

    abstract fun currencyInfoDAO(): CurrencyInfoDAO

    companion object {

        @Volatile // to make sure that all threads have immediate access to this property
        private var instance: CurrencyInfoDatabase? = null

        private val lock = Any() // to make sure that there will be no threads making the same thing at the same time

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CurrencyInfoDatabase::class.java,
                DATABASE_NAME
            ).fallbackToDestructiveMigration().build()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: buildDatabase(context).also { instance = it }
        }
    }
}