package com.example.materialmanagement.SearchActivity.RecyclerViewAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.materialmanagement.R
import java.text.SimpleDateFormat
import java.util.*

class OutRecyclerAdapter() : RecyclerView.Adapter<OutRecyclerAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) { //xml과 data 연결
        // val memo = list[position]

        val now = System.currentTimeMillis()
        var simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN).format(now)

        holder.in_date.text = simpleDateFormat
        //holder.item_num.text = position.toString()
        holder.item_num.text = "수주번호"
        holder.customer.text = position.toString()
        holder.storage.text = position.toString()
    }

    override fun getItemCount(): Int { // 리스트 만들 때 아이템 몇 개 있는지 카운트해서 리턴
        //return list.size
        return 20
    }

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val item_num = itemView.findViewById<TextView>(R.id.item_num)
        val in_date = itemView.findViewById<TextView>(R.id.in_date)
        val customer = itemView.findViewById<TextView>(R.id.customer)
        val storage = itemView.findViewById<TextView>(R.id.storage)


        //val root = itemView.findViewById<TableLayout>(R.id.root)
    }
}