package com.midsummer.currencylistdemo.ui.shared.currencyList

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.midsummer.currencylistdemo.MainApplication
import com.midsummer.currencylistdemo.data.CurrencyInfoRepository
import com.midsummer.currencylistdemo.internal.LogHelper
import com.midsummer.currencylistdemo.internal.SORT_ASC
import com.midsummer.currencylistdemo.internal.SORT_DESC
import com.midsummer.currencylistdemo.internal.singleArgViewModelFactory
import com.midsummer.currencylistdemo.pojo.CurrencyInfo
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by nienle on 03,March,2022
 * Midsummer.
 * Ping me at nienbkict@gmail.com
 * Happy coding ^_^
 */
class CurrencyListViewModel : ViewModel() {

    @Inject lateinit var repository: CurrencyInfoRepository

    init {
        MainApplication.instance.appComponent.inject(this)
    }

    private val _spinner = MutableLiveData(false)
    val spinner : LiveData<Boolean>
        get() =  _spinner

    private val _showContent = MutableLiveData(false)
    val showContent : LiveData<Boolean>
        get() =  _showContent

    private val _errText = MutableLiveData("")
    val errText : LiveData<String>
        get() =  _errText

    private val _dataList = MutableLiveData<List<CurrencyInfo>>(emptyList())
    val dataList : LiveData<List<CurrencyInfo>>
        get() =  _dataList


    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try{
                _spinner.postValue(true)
                block()

            }catch(t: Throwable){
                LogHelper.e(t)
            }finally {
                _spinner.postValue(false)
            }
        }
    }

    fun sortData(direction: Int) : Job{
        return launchDataLoad {
            val data : MutableList<CurrencyInfo> = arrayListOf()
            try{
                data.addAll(repository.fetchDataFromLocal())
                when(direction){
                    SORT_ASC -> {
                        data.sortBy { i -> i.name }
                    }
                    SORT_DESC -> {
                        data.sortByDescending { i -> i.name }
                    }
                }
            }catch(t: Throwable){
                LogHelper.e(t)
            } finally {
                delay(3000)
                if (data.isNotEmpty()) _dataList.postValue(data)
            }
        }
    }

    fun loadData() {
        launchDataLoad {
            delay(3000)
            _dataList.postValue(repository.fetchDataFromLocal())
            _showContent.postValue(true)
        }
    }

    fun fetchDataRemotely() {
        viewModelScope.launch {
            repository.fetchDataFromRemote()
        }
    }

    fun search(query: String){
        viewModelScope.launch {
            val list = repository.fetchDataFromLocal()
            if (query.isEmpty()) {
                _dataList.postValue(list)
                return@launch
            }
            val sortedList = list.filter { i -> i.name.contains(query, true) }
            _dataList.postValue(sortedList)

        }
    }
}