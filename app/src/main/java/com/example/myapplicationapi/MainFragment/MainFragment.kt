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
        init(view)
        setUpViewModel()

        /*viewModel.listChanges.observe(this.viewLifecycleOwner) {
            myAdapter!!.set(it)
        }*/

        binding!!.addButton.setOnClickListener {
            viewModel.addStaticSomethingModel()
        }

        binding!!.deleteButton.setOnClickListener {
            val item = myAdapter!!.myList.last()
            viewModel.deleteSomethingModel(item)
        }
        binding!!.updateButt.setOnClickListener {
            val item = myAdapter!!.myList.last()
            viewModel.updateSomethingModel(item)
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
}