package com.midsummer.currencylistdemo

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.midsummer.currencylistdemo.internal.LogHelper
import com.midsummer.currencylistdemo.internal.SORT_ASC
import com.midsummer.currencylistdemo.internal.SORT_DESC
import com.midsummer.currencylistdemo.pojo.CurrencyInfo
import com.midsummer.currencylistdemo.ui.shared.currencyList.CurrencyListFragment

class DemoActivity : AppCompatActivity() {

    private val currencyFragment by lazy {
        CurrencyListFragment.newInstance(){
            onItemClicked(it)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.containerFragment,currencyFragment).commitAllowingStateLoss()

        findViewById<Button>(R.id.btnLoad).setOnClickListener {
            currencyFragment.loadData()
        }

        findViewById<Button>(R.id.btnSort).setOnClickListener {
            val currentSortDirection = if (it.tag == null) SORT_ASC else it.tag as Int
            currencyFragment.sortData(currentSortDirection)
            it.tag = if (currentSortDirection == SORT_ASC) SORT_DESC else SORT_ASC
        }
    }


    private fun onItemClicked(data: CurrencyInfo?){
        data?.let {
            Toast.makeText(this, "Clicked: ${it.name} - ${it.symbol}", Toast.LENGTH_SHORT).show()
        }

    }
}