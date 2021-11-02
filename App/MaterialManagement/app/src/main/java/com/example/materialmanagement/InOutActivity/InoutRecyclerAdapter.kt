package com.example.materialmanagement.InOutActivity;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.materialmanagement.R
import java.text.SimpleDateFormat
import java.util.*

class InoutRecyclerAdapter() : RecyclerView.Adapter<InoutRecyclerAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_input, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) { //xml과 data 연결
        // val memo = list[position]

        val now = System.currentTimeMillis()
        var simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN).format(now)

        holder.in_date.text = simpleDateFormat
        holder.item_name.text = position.toString()
        holder.item_num.text = position.toString()
//        holder.root.setOnClickListener(object : View.OnClickListener{
//            override fun onClick(p0: View?) {
//                holder.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
//                    Toast.makeText(this,isChecked.toString(),Toast.LENGTH_SHORT).show()
//                }
//            }
//        })
    }

    override fun getItemCount(): Int { // 리스트 만들 때 아이템 몇 개 있는지 카운트해서 리턴
        //return list.size
        return 5
    }

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        //val checkbox = itemView.findViewById<CheckBox>(R.id.checkbox)
        val in_date = itemView.findViewById<TextView>(R.id.in_date)
        val item_name = itemView.findViewById<TextView>(R.id.item_name)
        val item_num = itemView.findViewById<TextView>(R.id.item_num)
        //val root = itemView.findViewById<TableLayout>(R.id.root)
    }
}
