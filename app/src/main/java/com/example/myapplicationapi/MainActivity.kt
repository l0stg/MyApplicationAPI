package com.example.myapplicationapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.example.myapplicationapi.AdapterRecyclerView.MyAdapter
import com.example.myapplicationapi.MainFragment.MainFragment
import com.example.myapplicationapi.MainFragment.MyViewModel

import com.example.myapplicationapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        supportFragmentManager.beginTransaction().replace(R.id.placeholder, MainFragment()).commit()
        //navController = Navigation.findNavController(this, R.id.nav_graph)
    }
}