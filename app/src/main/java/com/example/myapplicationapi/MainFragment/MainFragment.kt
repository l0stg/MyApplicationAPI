package com.example.myapplicationapi.MainFragment

import android.os.Bundle
import android.view.InputQueue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.models.SomethingDB
import com.example.data.repositories.SomethingRepository
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
        binding?.buttonSort?.setOnClickListener {
           viewModel.observeSortByName().observe(viewLifecycleOwner){
               myAdapter?.set(it)
           }
        }
        binding?.searchViewMain?.setOnQueryTextListener(this@MainFragment)
    }

  private fun searchDatabase(query: String){
      val searchQuery = "%$query%"

      viewModel.searchDatabase(searchQuery).observe(viewLifecycleOwner){
          myAdapter!!.set(it)
      }
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

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return true
    }
    override fun onQueryTextChange(p0: String?): Boolean {
        if (p0 != null){
            searchDatabase(p0)
        }
        return true
    }
}