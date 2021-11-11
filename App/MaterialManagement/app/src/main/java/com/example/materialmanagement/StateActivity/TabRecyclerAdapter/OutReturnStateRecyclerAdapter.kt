package com.example.materialmanagement.StateActivity.TabRecyclerAdapter;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.materialmanagement.R
import java.text.SimpleDateFormat
import java.util.*

class OutReturnStateRecyclerAdapter() : RecyclerView.Adapter<OutReturnStateRecyclerAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_state, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) { //xml과 data 연결
        val now = System.currentTimeMillis()
        var simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN).format(now)

        holder.item_num.text = position.toString() + 5
        holder.in_date.text = simpleDateFormat
        holder.item_name.text = position.toString() + 5
        holder.item_amount.text = position.toString() + 5
    }

    override fun getItemCount(): Int { // 리스트 만들 때 아이템 몇 개 있는지 카운트해서 리턴
        return 20
    }

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val item_num = itemView.findViewById<TextView>(R.id.item_num)
        val in_date = itemView.findViewById<TextView>(R.id.in_date)
        val item_name = itemView.findViewById<TextView>(R.id.item_name)
        val item_amount = itemView.findViewById<TextView>(R.id.item_amount)
    }
}
