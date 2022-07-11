package com.example.myapplicationapi.AdapterRecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplicationapi.Items
import com.example.myapplicationapi.R
import com.example.myapplicationapi.databinding.ItemViewBinding

class MyAdapter(private val onItemClicked: (Items)-> Unit):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

     private var myList: List<Items> = listOf()

    fun set(newList: List<Items>){
        this.myList = newList.toList()
        notifyDataSetChanged()
    }

    class MyViewHolder(view: View):RecyclerView.ViewHolder(view) {
        private val binding = ItemViewBinding.bind(view)
        fun bind(data: Items)
         = with(binding) {
            val nameBeer: String = "name: "
            tvNameMain.text = nameBeer + data.name
            tvDescription.text = data.description
            Glide.with(ivPerson.context)
                .load(data.imageAvatar)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(ivPerson)
            itemView.setOnClickListener {}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = myList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemClicked(myList[position])
        }
    }

    override fun getItemCount(): Int {
        return myList.size
    }
}