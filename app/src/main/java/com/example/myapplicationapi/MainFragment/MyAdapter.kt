package com.example.myapplicationapi.MainFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplicationapi.Items
import com.example.myapplicationapi.R
import com.example.myapplicationapi.databinding.ItemViewBinding

class MyAdapter(private val onItemClicked: ((position: Int)-> Unit)):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

     private var myList: List<Items> = listOf()

    fun set(newList: List<Items>){
        myList = newList.toList()
        notifyDataSetChanged()
    }

    class MyViewHolder(item: View):RecyclerView.ViewHolder(item) {
        private val binding = ItemViewBinding.bind(item)
        fun bind(data: Items) = with(binding) {
            tvName.text = tvName.text.toString() + data.name
            tvDescription.text = data.description
            Glide.with(ivPerson.context)
                .load(data.imageAvatar)
                .into(ivPerson)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(myList[position])
        holder.itemView.setOnClickListener {
            onItemClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return myList.size
    }
}