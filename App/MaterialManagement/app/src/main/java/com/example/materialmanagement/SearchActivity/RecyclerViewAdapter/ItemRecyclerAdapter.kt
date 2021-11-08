package com.example.materialmanagement.SearchActivity.RecyclerViewAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.materialmanagement.R
import java.text.SimpleDateFormat
import java.util.*

class ItemRecyclerAdapter() : RecyclerView.Adapter<ItemRecyclerAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_name, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) { //xml과 data 연결
        // val memo = list[position]

        holder.item_num.text = position.toString()
        holder.item_name.text = "품목명"
    }

    override fun getItemCount(): Int { // 리스트 만들 때 아이템 몇 개 있는지 카운트해서 리턴
        //return list.size
        return 20
    }

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val item_num = itemView.findViewById<TextView>(R.id.item_num)
        val item_name = itemView.findViewById<TextView>(R.id.item_name)


        //val root = itemView.findViewById<TableLayout>(R.id.root)
    }
}