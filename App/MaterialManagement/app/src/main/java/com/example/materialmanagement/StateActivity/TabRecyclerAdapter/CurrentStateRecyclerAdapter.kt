package com.example.materialmanagement.StateActivity.TabRecyclerAdapter;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.materialmanagement.DTO.CurrentData
import com.example.materialmanagement.R

class CurrentStateRecyclerAdapter(private var myRequest: List<CurrentData>) : RecyclerView.Adapter<CurrentStateRecyclerAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_current_state, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) { //xml과 data 연결
        holder.item_account.text = myRequest[position].comm_cd_nm
        holder.item_standard.text = myRequest[position].stan
        holder.item_name.text = myRequest[position].item_nm

        if(myRequest[position].qty.toString().endsWith(".0")){
            holder.item_amount.text = myRequest[position].qty.toInt().toString()
        } else {
            holder.item_amount.text = myRequest[position].qty.toString()
        }
    }

    override fun getItemCount(): Int { // 리스트 만들 때 아이템 몇 개 있는지 카운트해서 리턴
        return myRequest.size
    }

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val item_account = itemView.findViewById<TextView>(R.id.item_account)
        val item_standard = itemView.findViewById<TextView>(R.id.item_standard)
        val item_name = itemView.findViewById<TextView>(R.id.item_name)
        val item_amount = itemView.findViewById<TextView>(R.id.item_amount)
    }
}
