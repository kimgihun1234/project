package com.example.materialmanagement.SearchActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.materialmanagement.DTO.InInfo
import com.example.materialmanagement.DTO.OutInfo
import com.example.materialmanagement.DTO.StorageInfo
import com.example.materialmanagement.R
import com.example.materialmanagement.SearchActivity.RecyclerViewAdapter.OutRecyclerAdapter
import com.example.materialmanagement.SearchActivity.RecyclerViewAdapter.StorageRecyclerAdapter
import com.google.android.material.tabs.TabItem
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.IOException

//수주번호검색
class SearchOutOrder : AppCompatActivity() {
    private lateinit var refreshBtn : TabItem
    private lateinit var searchText : TextView

    private lateinit var outNumRecyclerAdapter: OutRecyclerAdapter // adapter 수정 필요, 아래도

    private lateinit var myRequest : String
    private var data : List<OutInfo> = emptyList()
    private var searchData : MutableList<OutInfo> = mutableListOf()
    private lateinit var recyclerView: RecyclerView

    private val client = OkHttpClient()
    private val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()
    private val gson = GsonBuilder().setPrettyPrinting().create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_out_order)

        searchText = findViewById(R.id.searchText)
        refreshBtn = findViewById(R.id.refreshBtn)

        val extras = intent.extras
        var itemNumber: String? = "검색결과없음"

        if (extras != null) {
            itemNumber = extras.getString("query")
            searchText.setText(itemNumber)
        }

        recyclerView = this.findViewById(R.id.in_num_list)

        if (itemNumber != null) {
            getOutOrder(itemNumber)
        }

        refreshBtn.setOnClickListener {
            if (itemNumber != null) {
                getOutOrder(itemNumber)
            }
            Toast.makeText(this, "refresh", Toast.LENGTH_SHORT).show()
        }
    }

    fun getOutOrder(itemNumber: String){
        val url = "http://101.101.208.223:8080/offerList"
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
                    data = Gson().fromJson(myRequest, Array<OutInfo>::class.java).toList()
                    runOnUiThread {
                        for(i in 0..data.size-1){
                            System.out.println(data[i].ex_requ_no + ", " +  data[i].cust_cd + ", "
                                    + data[i].cust_nm);
                            if(data[i].ex_requ_no == itemNumber.toString()){
                                searchData.add(data[i])
                            }
                        }
                        outNumRecyclerAdapter = OutRecyclerAdapter(searchData)

                        outNumRecyclerAdapter.setItemClickListener(object: OutRecyclerAdapter.OnItemClickListener{
                            override fun onClick(v: View, position: Int) {
                                val intent = Intent()
                                intent.putExtra("ex_requ_no", searchData[position].ex_requ_no)
                                intent.putExtra("cust_cd", searchData[position].cust_cd)
                                intent.putExtra("cust_nm", searchData[position].cust_nm)

                                setResult(RESULT_OK, intent)
                                finish()
                            }
                        })

                        recyclerView.apply {
                            layoutManager =
                                LinearLayoutManager(this@SearchOutOrder, LinearLayoutManager.VERTICAL,false)
                            adapter = outNumRecyclerAdapter
                        }
                    }
                }
            }
        })
    }
}