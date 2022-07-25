package com.example.myapplicationapi.DetailFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.data.models.DataBaseModel
import com.example.myapplicationapi.AdapterRecyclerView.ViewPagerAdapter
import com.example.myapplicationapi.R
import com.example.myapplicationapi.databinding.FragmentDetailBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment() : Fragment(R.layout.fragment_detail) {

    private val viewModel by viewModel<DetailViewModel>()
    private val binding: FragmentDetailBinding by viewBinding()
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

        with(binding) {
            myViewPager.adapter = viewPagerAdapter
            tvNameDetail.text = item.name
            tvDescriptionDetail.text = item.description
        }

        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.imageList.collect{
                    viewPagerAdapter?.set(it)
                    binding.sizeItem.text = it.count().toString()
                }
            }
        }
    }
}


