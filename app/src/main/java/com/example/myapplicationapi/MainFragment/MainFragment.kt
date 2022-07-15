package com.example.myapplicationapi.MainFragment

import android.os.Bundle
import android.view.InputQueue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationapi.AdapterRecyclerView.MyAdapter
import com.example.myapplicationapi.Screens.Screens
import com.example.myapplicationapi.databinding.FragmentMainBinding



class MainFragment : Fragment(), SearchView.OnQueryTextListener {


    private var binding: FragmentMainBinding? = null
    private val viewModel by viewModels<MyViewModel>()
    private var myAdapter: MyAdapter? = null
    private val screens: Screens = Screens()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        setUpViewModel()
        var isLoading = false
        var pageNum = 1

        binding?.buttonSort?.setOnClickListener {
            buttonSortName()
        }

        binding?.searchViewMain?.setOnQueryTextListener(this@MainFragment)


        viewModel.integer.observe(viewLifecycleOwner){
            isLoading = false
        }

        binding!!.myRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                val total = myAdapter!!.itemCount
                if (!isLoading) {
                    if ((visibleItemCount + pastVisibleItem) >= total) {
                        isLoading = true
                        pageNum++
                        viewModel.getAllItemList(pageNum)
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })


    }

    private fun setUpViewModel(){
        viewModel.observeAllSomething().observe(viewLifecycleOwner) {
            myAdapter?.set(it)
        }
    }

    private fun init(view: View){
        myAdapter = MyAdapter{
            screens.routeToDetailFragment(it, view)
        }
        binding?.apply {
            myRecyclerView.layoutManager = LinearLayoutManager(activity)
            myRecyclerView.adapter = myAdapter
        }
    }
  //поиск по имени
    private fun searchDatabase(query: String){
        val searchQuery = "%$query%"
        viewModel.searchDatabase(searchQuery).observe(viewLifecycleOwner){
            it.map { FragmentDataModel(it.name, it.description, it.imageAvatar) }
            myAdapter!!.set(it)
        }
    }
    override fun onQueryTextSubmit(p0: String?): Boolean {
        return true
    }
    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null){
            searchDatabase(query)
        }
        return true
    }

    //Сортировка по именни
    private fun buttonSortName(){
        viewModel.observeSortByName().observe(viewLifecycleOwner){
            myAdapter?.set(it)
        }
    }
}
