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
import com.example.materialmanagement.DTO.StorageInfo
import com.example.materialmanagement.R
import com.example.materialmanagement.SearchActivity.RecyclerViewAdapter.StorageRecyclerAdapter
import com.google.android.material.tabs.TabItem
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

//창고검색
class SearchStorage : AppCompatActivity() {
    private lateinit var refreshBtn : TabItem
    private lateinit var searchText : TextView

    private lateinit var orderBasic : Button

    private lateinit var storageRecyclerAdapter: StorageRecyclerAdapter

    private lateinit var myRequest : String
    private var data : List<StorageInfo> = emptyList()
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_storage)

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

        val client = OkHttpClient()
        val url = "http://101.101.208.223:8080/locationList"
        //-> String stor_cd stor_nm loca_cd loca_nm
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
                    data = Gson().fromJson(myRequest, Array<StorageInfo>::class.java).toList()
                    runOnUiThread {
                        for(i in 0..data.size-1){
                            System.out.println(data[i].stor_cd + ", " +  data[i].stor_nm + ", "
                                    + data[i].loca_cd+ ", " +  data[i].loca_nm);
                        }
                        storageRecyclerAdapter = StorageRecyclerAdapter(data)

                        storageRecyclerAdapter.setItemClickListener(object: StorageRecyclerAdapter.OnItemClickListener{
                            override fun onClick(v: View, position: Int) {
                                // 클릭 시 이벤트 작성
                                val intent = Intent()
                                intent.putExtra("stor_cd", data[position].stor_cd)
                                intent.putExtra("stor_nm", data[position].stor_nm)
                                intent.putExtra("loca_cd", data[position].loca_cd)

                                setResult(RESULT_OK, intent)
                                finish()
                            }
                        })

                        recyclerView.apply {
                            layoutManager =
                                LinearLayoutManager(this@SearchStorage, LinearLayoutManager.VERTICAL,false)
                            adapter = storageRecyclerAdapter
                        }
                    }
                }
            }
        })

        refreshBtn.setOnClickListener {
            Toast.makeText(this, "refresh", Toast.LENGTH_SHORT).show()
        }
    }
}