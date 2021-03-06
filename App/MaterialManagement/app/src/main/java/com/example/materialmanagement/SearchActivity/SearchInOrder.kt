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
import com.example.materialmanagement.DTO.StorageInfo
import com.example.materialmanagement.R
import com.example.materialmanagement.SearchActivity.RecyclerViewAdapter.InRecyclerAdapter
import com.example.materialmanagement.SearchActivity.RecyclerViewAdapter.StorageRecyclerAdapter
import com.google.android.material.tabs.TabItem
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.IOException

//발주번호검색
class SearchInOrder : AppCompatActivity() {
    private lateinit var refreshBtn : TabItem
    private lateinit var searchText : TextView

    private lateinit var inRecyclerAdapter: InRecyclerAdapter

    private lateinit var myRequest : String
    private var data : List<InInfo> = emptyList()
    private var searchData : MutableList<InInfo> = mutableListOf()
    private lateinit var recyclerView: RecyclerView

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_in_order)

        searchText = findViewById(R.id.searchText)
        refreshBtn = findViewById(R.id.refreshBtn)

        val extras = intent.extras
        var itemNumber: String? = "검색결과없음"

        if (extras != null) {
            itemNumber = extras.getString("query") // 값 꺼내기
            searchText.setText(itemNumber)
        }

        if (itemNumber != null) {
            getInOrder(itemNumber)
        }

        recyclerView = this.findViewById(R.id.in_num_list)

        refreshBtn.setOnClickListener {
            if (itemNumber != null) {
                getInOrder(itemNumber)
            }
            Toast.makeText(this, "refresh", Toast.LENGTH_SHORT).show()
        }
    }

    fun getInOrder(itemNumber : String){
        val url = "http://101.101.208.223:8080/orderList"
        val request: Request = Request.Builder()
            .url(url)
            .get()
            .build();

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread{ Log.d("test","fail")}
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    myRequest = response.body!!.string()
                    data = Gson().fromJson(myRequest, Array<InInfo>::class.java).toList()
                    runOnUiThread {
                        for (i in 0..data.size - 1) {
                            System.out.println(
                                data[i].plord_no + ", " + data[i].cust_nm + ", "
                                        + data[i].cust_cd
                            )
                            if(data[i].plord_no.contains(itemNumber)){
                                searchData.add(data[i])
                            }
                        }
                        inRecyclerAdapter = InRecyclerAdapter(searchData)

                        inRecyclerAdapter.setItemClickListener(object :
                            InRecyclerAdapter.OnItemClickListener {
                            override fun onClick(v: View, position: Int) {
                                // 클릭 시 이벤트 작성
                                val intent = Intent()
                                intent.putExtra("plord_no", searchData[position].plord_no)
                                intent.putExtra("cust_nm", searchData[position].cust_nm)
                                intent.putExtra("cust_cd", searchData[position].cust_cd)
                                intent.putExtra("plord_dt", searchData[position].plord_dt)

                                setResult(RESULT_OK, intent)
                                finish()
                            }
                        })
                        recyclerView.apply {
                            layoutManager =
                                LinearLayoutManager(
                                    this@SearchInOrder,
                                    LinearLayoutManager.VERTICAL,
                                    false
                                )
                            adapter = inRecyclerAdapter
                        }
                    }
                }
            }
        })
    }
}