package com.example.materialmanagement.SearchActivity

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
import com.example.materialmanagement.DTO.CustomerInfo
import com.example.materialmanagement.R
import com.example.materialmanagement.SearchActivity.RecyclerViewAdapter.CustomerRecyclerAdapter
import com.google.android.material.tabs.TabItem
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException


class SearchCustomer : AppCompatActivity() {
    private lateinit var refreshBtn : TabItem
    private lateinit var searchText : TextView

    private lateinit var customerRecyclerAdapter: CustomerRecyclerAdapter

    private lateinit var myRequest : String
    private var data : List<CustomerInfo> = emptyList()
    private var searchData : MutableList<CustomerInfo> = mutableListOf()
    private lateinit var recyclerView: RecyclerView

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_customer)

        searchText = findViewById(R.id.searchText)
        refreshBtn = findViewById(R.id.refreshBtn)

        val extras = intent.extras
        var itemNumber: String? = "검색결과없음"

        if (extras != null) {
            itemNumber = extras.getString("query") // 값 꺼내기
            searchText.setText(itemNumber)
        }

        if (itemNumber != null) {
            getCustomer(itemNumber)
        }

        recyclerView = this.findViewById(R.id.in_num_list)

        refreshBtn.setOnClickListener {
            if (itemNumber != null) {
                getCustomer(itemNumber)
            }
            Toast.makeText(this, "refresh", Toast.LENGTH_SHORT).show()
        }
    }

    fun getCustomer(itemNumber : String){
        val url = "http://101.101.208.223:8080/customerList"
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
                    data = Gson().fromJson(myRequest, Array<CustomerInfo>::class.java).toList()
                    runOnUiThread {
                        for (i in 0..data.size - 1) {
                            System.out.println(
                                data[i].cust_cd + ", " + data[i].cust_nm
                            )
                            if(data[i].cust_nm.contains(itemNumber)){
                                searchData.add(data[i])
                            }
                        }
                        customerRecyclerAdapter = CustomerRecyclerAdapter(searchData)

                        customerRecyclerAdapter.setItemClickListener(object :
                            CustomerRecyclerAdapter.OnItemClickListener {
                            override fun onClick(v: View, position: Int) {
                                val intent = Intent()
                                intent.putExtra("cust_nm", searchData[position].cust_nm)
                                intent.putExtra("cust_cd", searchData[position].cust_cd)

                                setResult(RESULT_OK, intent)
                                finish()
                            }
                        })
                        recyclerView.apply {
                            layoutManager =
                                LinearLayoutManager(
                                    this@SearchCustomer,
                                    LinearLayoutManager.VERTICAL,
                                    false
                                )
                            adapter = customerRecyclerAdapter
                        }
                    }
                }
            }
        })
    }
}