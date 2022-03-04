package com.midsummer.currencylistdemo.ui.shared.currencyList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import com.midsummer.currencylistdemo.MainApplication
import com.midsummer.currencylistdemo.data.CurrencyInfoRepository
import com.midsummer.currencylistdemo.data.local.CurrencyInfoDatabase
import com.midsummer.currencylistdemo.data.remote.CurrencyInfoAPIService
import com.midsummer.currencylistdemo.databinding.FragmentCurrencyListBinding
import com.midsummer.currencylistdemo.databinding.RvItemCurrencyInfoBinding
import com.midsummer.currencylistdemo.internal.LogHelper
import com.midsummer.currencylistdemo.pojo.CurrencyInfo
import kotlinx.coroutines.Job
import javax.inject.Inject

/**
 * Created by nienle on 03,March,2022
 * Midsummer.
 * Ping me at nienbkict@gmail.com
 * Happy coding ^_^
 */
class CurrencyListFragment(
    private val onSelect: (CurrencyInfo?) -> Unit
) : Fragment(){

    private lateinit var viewModel: CurrencyListViewModel
    private lateinit var _binding : FragmentCurrencyListBinding
    private val adapter = CurrencyListAdapter(arrayListOf()){
        onSelect(it)
    }
    private val binding get() = _binding

    companion object {

        fun newInstance(onSelect: (CurrencyInfo?) -> Unit) : CurrencyListFragment {
            val i = CurrencyListFragment(onSelect)
            return i
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrencyListBinding.inflate(inflater, container, false)
        _binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerViewContent.apply {
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = this@CurrencyListFragment.adapter
        }
        binding.recyclerViewContent.addItemDecoration(DividerItemDecoration(
            requireContext(), DividerItemDecoration.VERTICAL
        ))
        viewModel.dataList.observe(viewLifecycleOwner) {
            adapter.setupData(it)
        }
        viewModel.errText.observe(viewLifecycleOwner){
            LogHelper.d(it)
        }
        binding.model = viewModel


        binding.edtSearch.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    if (p0 != null) viewModel.search(p0)
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    if (p0 != null) viewModel.search(p0)
                    return true
                }

            }
        )
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainApplication.instance.appComponent.inject(this)
        viewModel =
            ViewModelProvider(this)[CurrencyListViewModel::class.java]
    }

    override fun onResume() {
        super.onResume()
        fetchDataFromRemote()
    }

    private val runningJobs : MutableList<Job> = arrayListOf()
    fun sortData(direction: Int) {
        runningJobs.forEach { job -> job.cancel() }
        runningJobs.clear()
        runningJobs.add(viewModel.sortData(direction))
    }

    fun loadData() = viewModel.loadData()
    private fun fetchDataFromRemote() = viewModel.fetchDataRemotely()
}