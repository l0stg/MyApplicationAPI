package com.example.myapplicationapi.MainFragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplicationapi.AdapterRecyclerView.MyAdapter
import com.example.myapplicationapi.R
import com.example.myapplicationapi.Screens.Screens
import com.example.myapplicationapi.databinding.FragmentMainBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.http.Query


class MainFragment : Fragment(R.layout.fragment_main), SearchView.OnQueryTextListener  {

    private val binding: FragmentMainBinding by viewBinding()
    private val viewModel by viewModel<MainViewModel>()
    private var myAdapter: MyAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        updateDataInUI()
    }

    private fun updateDataInUI() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.list.collect {
                    myAdapter?.set(it)
                }
            }
        }
    }

    private fun init(){
       myAdapter = MyAdapter{
           viewModel.routeToDetail(it)
       }

        with(binding) {
            apply {
                myRecyclerView.layoutManager = LinearLayoutManager(activity)
                myRecyclerView.adapter = myAdapter
            }
            buttonSort.setOnClickListener {
                buttonSortName()
            }
            searchViewMain.setOnQueryTextListener(this@MainFragment)
        }
    }

    private fun searchDatabase(searchQuery: String){
        viewModel.searchDatabase(searchQuery)
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            val searchQuery = "%$query%"
            searchDatabase(searchQuery)
        }
        return true
    }

    private fun buttonSortName(){
        viewModel.observeSortByName()
    }
}
