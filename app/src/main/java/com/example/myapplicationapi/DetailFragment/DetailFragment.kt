package com.example.myapplicationapi.DetailFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.data.models.DataBaseModel
import com.example.myapplicationapi.AdapterRecyclerView.ViewPagerAdapter
import com.example.myapplicationapi.R
import com.example.myapplicationapi.Screens.Screens
import com.example.myapplicationapi.databinding.FragmentDetailBinding

class DetailFragment() : Fragment() {

    private var binding: FragmentDetailBinding? = null
    private val screens: Screens = Screens()

    private var viewPager: ViewPager2? = null
    private var viewPagerAdapter: ViewPagerAdapter? = null
    private var imageList: ArrayList<String>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemOn = arguments?.getSerializable("Item") as DataBaseModel
        //для примера
        imageList = arrayListOf()
        with(imageList!!) {
            add(itemOn.imageAvatar.toString())
            add("https://images.punkapi.com/v2/202.png")
            add(itemOn.imageAvatar.toString())
            add(itemOn.imageAvatar.toString())
            add(itemOn.imageAvatar.toString())
        }

        viewPagerAdapter = ViewPagerAdapter{
            val bundle = Bundle()
            bundle.putSerializable("Item",it)
            findNavController().navigate(R.id.action_detailFragment_to_imageFragment, bundle)
        }

        viewPager = binding!!.myViewPager
        viewPagerAdapter!!.set(imageList!!)
        viewPager!!.adapter = viewPagerAdapter
        binding!!.sizeItem.text = viewPagerAdapter!!.itemCount.toString()
        binding?.tvNameDetail?.text = itemOn.name
        binding?.tvDescriptionDetail?.text = itemOn.description

    }
}


