package com.example.materialmanagement.SearchActivity.RecyclerViewAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.materialmanagement.R

class StorageRecyclerAdapter() : RecyclerView.Adapter<StorageRecyclerAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_storage, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) { //xml과 data 연결
        // val memo = list[position]
        holder.item_storage_num.text = "    " + "123124"
        holder.item_storage_name.text = "서울 아산 창고 / A구역"
    }

    override fun getItemCount(): Int { // 리스트 만들 때 아이템 몇 개 있는지 카운트해서 리턴
        //return list.size
        return 20
    }

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val item_storage_num = itemView.findViewById<TextView>(R.id.item_storage_num)
        val item_storage_name = itemView.findViewById<TextView>(R.id.item_storage_name)


        //val root = itemView.findViewById<TableLayout>(R.id.root)
    }
}