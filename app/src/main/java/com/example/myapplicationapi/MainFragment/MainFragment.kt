package com.example.myapplicationapi.MainFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationapi.AdapterRecyclerView.MyAdapter
import com.example.myapplicationapi.Screens.Screens
import com.example.myapplicationapi.databinding.FragmentMainBinding
class MainFragment : Fragment() {

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

        binding?.myRecyclerView?.layoutManager = LinearLayoutManager(activity)

        myAdapter = MyAdapter{
            screens.routeToDetailFragment(it, view)
        }

        binding?.myRecyclerView?.adapter = myAdapter

        binding!!.buttonSort.setOnClickListener {
            viewModel.sortByName()
        }

        //поиск по имени

        binding!!.searchViewMain.clearFocus()
        binding!!.searchViewMain.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
               // viewModel.filterList(p0)
                return true
            }
        })



        viewModel.listChanges.observe(this.viewLifecycleOwner) {
            myAdapter!!.set(it)
        }
    }
}