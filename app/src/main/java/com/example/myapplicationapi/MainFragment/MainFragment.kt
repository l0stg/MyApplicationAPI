package com.example.myapplicationapi.MainFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplicationapi.AdapterRecyclerView.MyAdapter
import com.example.myapplicationapi.R
import com.example.myapplicationapi.Screens.Screens
import com.example.myapplicationapi.databinding.FragmentMainBinding
import retrofit2.http.Query


class MainFragment : Fragment(R.layout.fragment_main), SearchView.OnQueryTextListener  {


    private val binding: FragmentMainBinding by viewBinding()
    private val viewModel by viewModels<MyViewModel>()
    private var myAdapter: MyAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setUpViewModel()
        //var isLoading = false




        /*viewModel.integer.observe(viewLifecycleOwner){
            isLoading = false
        }*/

/*        binding!!.myRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){ // вынести логику пагинации во вью модел
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                val total = myAdapter!!.itemCount
                if (viewModel.integer.value!! < 10) {
                    if (!isLoading) {
                        if ((visibleItemCount + pastVisibleItem) >= total) {
                            isLoading = true
                            viewModel.getAllItemList(viewModel.integer.value!!)
                        }
                    }
                }else Toast.makeText(activity, "Последняя страница", Toast.LENGTH_SHORT).show()
                super.onScrolled(recyclerView, dx, dy)
            }
        })*/
    }

    private fun setUpViewModel(){
        viewModel.observeAllSomething().observe(viewLifecycleOwner) {
            myAdapter?.set(it)
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
        viewModel.searchDatabase(searchQuery).observe(viewLifecycleOwner){
            myAdapter!!.set(it)
        }
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
        viewModel.observeSortByName().observe(viewLifecycleOwner){
            myAdapter?.set(it)
        }
    }
}
