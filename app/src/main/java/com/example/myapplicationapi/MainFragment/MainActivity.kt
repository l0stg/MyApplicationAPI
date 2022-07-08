package com.example.myapplicationapi.MainFragment

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationapi.DetailFragment.DetailFragment
import com.example.myapplicationapi.Items
import com.example.myapplicationapi.R
import com.example.myapplicationapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var myAdapter: MyAdapter? = null
    private val viewModel by viewModels<MyViewModel>()

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        val recyclerView: RecyclerView? = binding?.myRecyclerView
        myAdapter =  MyAdapter {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
        }
        recyclerView?.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView?.adapter = myAdapter


        viewModel.listChanges.observe(this){
            myAdapter!!.set(it)
        }
        fun routeSecondFragment(model: Items){
            val intent = Intent(this, DetailFragment::class.java).apply {
                putExtra(DetailFragment, model)
            }
        }
    }
}