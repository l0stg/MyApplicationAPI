package com.example.myapplicationapi.MainFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationapi.AdapterRecyclerView.MyAdapter
import com.example.myapplicationapi.databinding.FragmentMainBinding
class MainFragment : Fragment() {

    private var fragmentBlankBinding: FragmentMainBinding? = null
    private val viewModel by viewModels<MyViewModel>()
    private var myAdapter: MyAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBlankBinding = FragmentMainBinding.inflate(inflater, container, false)
        return fragmentBlankBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBlankBinding?.myRecyclerView?.layoutManager = LinearLayoutManager(activity)
        myAdapter = MyAdapter{
            viewModel.routeToDetailFragment(it, view)
        }
        fragmentBlankBinding?.myRecyclerView?.adapter = myAdapter
        viewModel.listChanges.observe(this.viewLifecycleOwner) {
            myAdapter!!.set(it)
        }
    }
}