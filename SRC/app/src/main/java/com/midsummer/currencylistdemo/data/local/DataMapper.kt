package com.midsummer.currencylistdemo.data.local

import com.midsummer.currencylistdemo.pojo.CurrencyInfo

/**
 * Created by nienle on 02,March,2022
 * Midsummer.
 * Ping me at nienbkict@gmail.com
 * Happy coding ^_^
 */

fun CurrencyInfoEntity.toCurrencyInfo() = CurrencyInfo(
    this.id,
    this.name,
    this.symbol
)

fun List<CurrencyInfoEntity>.toCurrencyInfoList() = this.map { it.toCurrencyInfo() }

fun CurrencyInfo.toCurrencyInfoEntity() = CurrencyInfoEntity(
    this.id,
    this.name,
    this.symbol
)

fun List<CurrencyInfo>.toCurrencyInfoEntityList() = this.map { it.toCurrencyInfoEntity() }