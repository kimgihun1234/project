package com.example.materialmanagement.SearchActivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.materialmanagement.DTO.InInfo
import com.example.materialmanagement.DTO.ItemInfo
import com.example.materialmanagement.DTO.StorageInfo
import com.example.materialmanagement.R
import com.example.materialmanagement.SearchActivity.RecyclerViewAdapter.ItemRecyclerAdapter
import com.example.materialmanagement.SearchActivity.RecyclerViewAdapter.StorageRecyclerAdapter
import com.google.android.material.tabs.TabItem
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.IOException

//품목 검색
class SearchItem : AppCompatActivity() {
    private lateinit var refreshBtn : TabItem
    private lateinit var searchText : TextView

    private lateinit var orderBasic : Button

    private lateinit var itemRecyclerView: ItemRecyclerAdapter

    private lateinit var myRequest : String
    private var data : List<ItemInfo> = emptyList()
    private var searchData : MutableList<ItemInfo> = mutableListOf()
    private lateinit var recyclerView: RecyclerView

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_item)

        searchText = findViewById(R.id.searchText)
        refreshBtn = findViewById(R.id.refreshBtn)
        orderBasic = findViewById(R.id.orderBasic)

        val extras = intent.extras
        var itemNumber: String? = "검색결과없음"

        if (extras != null) {
            itemNumber = extras.getString("query") // 값 꺼내기
            searchText.setText(itemNumber)
        }

        orderBasic.setOnClickListener { // 반환값 테스트
            Toast.makeText(this, "기본순", Toast.LENGTH_SHORT).show()
        }

        recyclerView = this.findViewById(R.id.in_num_list)
        if (itemNumber != null) {
            getItem(itemNumber)
        }

        refreshBtn.setOnClickListener {
            if (itemNumber != null) {
                getItem(itemNumber)
            }
            Toast.makeText(this, "refresh", Toast.LENGTH_SHORT).show()
        }
    }

    fun getItem(itemNumber:String){
        val url = "http://101.101.208.223:8080/itemList"
        val request: Request = Request.Builder()
            .url(url)
            .get()
            .build();

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread{ Log.d("test","failt")}
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    myRequest = response.body!!.string()
                    data = Gson().fromJson(myRequest, Array<ItemInfo>::class.java).toList()
                    runOnUiThread {
                        for(i in 0..data.size-1){
                            System.out.println(data[i].item_cd + ", " +  data[i].item_nm);
                            if(data[i].item_nm == itemNumber.toString()){
                                searchData.add(data[i])
                            }
                        }
                        itemRecyclerView = ItemRecyclerAdapter(searchData)

                        itemRecyclerView.setItemClickListener(object: ItemRecyclerAdapter.OnItemClickListener{
                            override fun onClick(v: View, position: Int) {
                                // 클릭 시 이벤트 작성
                                val intent = Intent()
                                intent.putExtra("item_cd", searchData[position].item_cd)
                                intent.putExtra("item_nm", searchData[position].item_nm)

                                setResult(RESULT_OK, intent)
                                finish()
                            }
                        })

                        recyclerView.apply {
                            layoutManager =
                                LinearLayoutManager(this@SearchItem, LinearLayoutManager.VERTICAL,false)
                            adapter = itemRecyclerView
                        }
                    }
                }
            }
        })
    }
}