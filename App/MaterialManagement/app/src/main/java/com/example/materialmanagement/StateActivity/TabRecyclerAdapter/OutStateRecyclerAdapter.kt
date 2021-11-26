package com.example.materialmanagement.StateActivity.TabRecyclerAdapter;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.materialmanagement.DTO.UnstoringListGetData
import com.example.materialmanagement.R
import com.example.materialmanagement.SearchActivity.RecyclerViewAdapter.ItemRecyclerAdapter

class OutStateRecyclerAdapter(private var myRequest: List<UnstoringListGetData>) : RecyclerView.Adapter<OutStateRecyclerAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_state, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) { //xml과 data 연결
        //holder.item_num.text = myRequest[position].item_cd
        holder.in_date.text = myRequest[position].ex_dt
        holder.item_name.text = myRequest[position].item_nm
        if(myRequest[position].qty.toString().endsWith(".0")){
            holder.item_amount.text = myRequest[position].qty.toInt().toString()
        } else {
            holder.item_amount.text = myRequest[position].qty.toString()
        }

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int { // 리스트 만들 때 아이템 몇 개 있는지 카운트해서 리턴
        return myRequest.size
    }

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        //val item_num = itemView.findViewById<TextView>(R.id.item_num)
        val in_date = itemView.findViewById<TextView>(R.id.in_date)
        val item_name = itemView.findViewById<TextView>(R.id.item_name)
        val item_amount = itemView.findViewById<TextView>(R.id.item_amount)
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: ItemRecyclerAdapter.OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : ItemRecyclerAdapter.OnItemClickListener
}
