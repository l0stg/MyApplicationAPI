package com.example.myapplicationapi.AdapterRecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.models.DataBaseModel
import com.example.myapplicationapi.R
import com.example.myapplicationapi.databinding.ViewPagerItemBinding

class ViewPagerAdapter(private val onItemClicked: (String)-> Unit): RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    private var myList: List<String> = listOf()

    fun set(newList: List<String>){
        this.myList = newList.toList()
        notifyDataSetChanged()
    }

    class ViewPagerViewHolder(view: View):RecyclerView.ViewHolder(view) {
        private val binding = ViewPagerItemBinding.bind(view)
        fun bind(data: String){
            with(binding) {
                Glide.with(imDetailImage.context)
                .load(data)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imDetailImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_pager_item, parent, false)
        return ViewPagerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val item = myList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemClicked(myList[position])
        }
    }
    override fun getItemCount(): Int = myList.size


}