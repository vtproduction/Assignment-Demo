package com.midsummer.currencylistdemo.ui.shared.currencyList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.midsummer.currencylistdemo.R
import com.midsummer.currencylistdemo.databinding.RvItemCurrencyInfoBinding
import com.midsummer.currencylistdemo.pojo.CurrencyInfo
import javax.inject.Singleton


/**
 * Created by nienle on 03,March,2022
 * Midsummer.
 * Ping me at nienbkict@gmail.com
 * Happy coding ^_^
 */
class CurrencyListAdapter(
  private val data: MutableList<CurrencyInfo>,
  private val onSelect: (CurrencyInfo?) -> Unit
) : RecyclerView.Adapter<CurrencyInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyInfoViewHolder {
        val itemViewBinding: RvItemCurrencyInfoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.rv_item_currency_info,
            parent,
            false
        )
        return CurrencyInfoViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: CurrencyInfoViewHolder, position: Int) {
        holder.bind(data[position], onSelect)
    }

    override fun getItemCount() = data.size


    fun setupData(data: List<CurrencyInfo>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}



class CurrencyInfoViewHolder(
    private val viewBinding : RvItemCurrencyInfoBinding
) : RecyclerView.ViewHolder(viewBinding.root) {
    fun bind(data: CurrencyInfo, onSelect: (CurrencyInfo?) -> Unit) {
        viewBinding.model = data
        viewBinding.root.setOnClickListener {
            onSelect(data)
        }
    }
}


