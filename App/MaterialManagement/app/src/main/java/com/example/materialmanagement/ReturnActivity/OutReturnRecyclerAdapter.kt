package com.example.materialmanagement.ReturnActivity;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.materialmanagement.DTO.StoreStateInfo
import com.example.materialmanagement.R
import java.text.SimpleDateFormat
import java.util.*

class OutReturnRecyclerAdapter(private var myRequest: List<StoreStateInfo>) : RecyclerView.Adapter<OutReturnRecyclerAdapter.MyViewHolder>() {
    var isUnselectedAll : Boolean = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_input, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) { //xml과 data 연결
        val now = System.currentTimeMillis()
        var simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN).format(now)

        if (!isUnselectedAll){
            holder.checkbox.setChecked(false);
        } else {
            holder.checkbox.setChecked(true)
        }

        holder.in_date.text = simpleDateFormat
        holder.item_name.text = myRequest[position].item_nm
        if(myRequest[position].qty.toString().endsWith(".0")){
            holder.item_num.text = myRequest[position].qty.toInt().toString()
        } else {
            holder.item_num.text = myRequest[position].qty.toString()
        }

        // (1) 리스트 내 항목 클릭 시 onClick() 호출
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
            holder.checkbox.toggle()
        }
    }

    override fun getItemCount(): Int { // 리스트 만들 때 아이템 몇 개 있는지 카운트해서 리턴
        return myRequest.size
    }

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val checkbox = itemView.findViewById<CheckBox>(R.id.checkbox)
        val in_date = itemView.findViewById<TextView>(R.id.in_date)
        val item_name = itemView.findViewById<TextView>(R.id.item_name)
        val item_num = itemView.findViewById<TextView>(R.id.item_num)
    }

    // (2) 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener

    fun checkboxReset() {
        isUnselectedAll = false
        notifyDataSetChanged()
    }
}
