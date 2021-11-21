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
import com.example.materialmanagement.DTO.BarcodeInfo
import com.example.materialmanagement.DTO.BarcodePostInfo
import com.example.materialmanagement.DTO.StorageInfo
import com.example.materialmanagement.R
import com.example.materialmanagement.SearchActivity.RecyclerViewAdapter.BarcodeRecyclerAdapter
import com.example.materialmanagement.SearchActivity.RecyclerViewAdapter.InRecyclerAdapter
import com.example.materialmanagement.SearchActivity.RecyclerViewAdapter.StorageRecyclerAdapter
import com.google.android.material.tabs.TabItem
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.IOException
import java.util.*


class SearchBarcode : AppCompatActivity() {
    private lateinit var refreshBtn : TabItem
    private lateinit var searchText : TextView

    private lateinit var barcodeRecyclerAdapter: BarcodeRecyclerAdapter

    private lateinit var myRequest : String
    private var data : List<BarcodeInfo> = emptyList()
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_barcode)

        searchText = findViewById(R.id.searchText)
        refreshBtn = findViewById(R.id.refreshBtn)

        val extras = intent.extras
        var barcodeNumber: String? = "검색결과없음"

        if (extras != null) {
            barcodeNumber = extras.getString("query") // 값 꺼내기
            searchText.setText(barcodeNumber)
        }

        recyclerView = this.findViewById(R.id.in_num_list)

        val client = OkHttpClient()

        val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()
        val gson = GsonBuilder().setPrettyPrinting().create()
        val postData = BarcodePostInfo(barcodeNumber.toString())
        val jsonString = gson.toJson(postData)

        val formBody: RequestBody = RequestBody.create(JSON, jsonString)

        val url = "http://101.101.208.223:8080/barcode"
        val request: Request = Request.Builder()
            .url(url)
            .post(formBody)
            .build();

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread{ Log.d("test","failt")}
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    myRequest = response.body!!.string()
                    val requestList = "[$myRequest]"
                    System.out.println(myRequest)
                    data = Gson().fromJson(requestList, Array<BarcodeInfo>::class.java).toList()
                    runOnUiThread {
                        for(i in 0..data.size-1){
                            System.out.println(data[i].item_cd + ", " +  data[i].item_nm + ", "
                                    + data[i].qty);
                        }
                        barcodeRecyclerAdapter = BarcodeRecyclerAdapter(data)

                        barcodeRecyclerAdapter.setItemClickListener(object: BarcodeRecyclerAdapter.OnItemClickListener{
                            override fun onClick(v: View, position: Int) {
                                // 클릭 시 이벤트 작성
                                val intent = Intent()
                                intent.putExtra("item_cd", data[position].item_cd)
                                intent.putExtra("item_nm", data[position].item_nm)
                                intent.putExtra("qty", data[position].qty)

                                setResult(RESULT_OK, intent)
                                finish()
                            }
                        })

                        recyclerView.apply {
                            layoutManager =
                                LinearLayoutManager(this@SearchBarcode, LinearLayoutManager.VERTICAL,false)
                            adapter = barcodeRecyclerAdapter
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