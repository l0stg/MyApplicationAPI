package com.example.myapplicationapi.DetailFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.data.models.DataBaseModel
import com.example.myapplicationapi.AdapterRecyclerView.ViewPagerAdapter
import com.example.myapplicationapi.R
import com.example.myapplicationapi.Screens.Screens
import com.example.myapplicationapi.databinding.FragmentDetailBinding

class DetailFragment() : Fragment(R.layout.fragment_detail) {

    private val viewModel: DetailViewModel by viewModels()
    private val binding: FragmentDetailBinding by viewBinding()
    private var viewPager: ViewPager2? = null
    private var viewPagerAdapter: ViewPagerAdapter? = null

    companion object{
        private const val ITEM = "ITEM"
        fun newInstance(item: DataBaseModel) = DetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable(ITEM, item)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item = arguments?.getSerializable(ITEM) as DataBaseModel
        viewModel.addToList(item)
        viewPagerAdapter = ViewPagerAdapter{
            viewModel.routeToImageFragment(it)
        }
        viewPager = binding.myViewPager
        viewModel.imageList.observe(viewLifecycleOwner){
            viewPagerAdapter?.set(it)
        }
        viewPager?.adapter = viewPagerAdapter
        with(binding) {
            sizeItem.text = viewModel.imageList.value?.size.toString()
            tvNameDetail.text = item.name
            tvDescriptionDetail.text = item.description
        }


    }
}


