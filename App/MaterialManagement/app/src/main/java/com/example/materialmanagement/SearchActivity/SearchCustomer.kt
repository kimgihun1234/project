package com.example.materialmanagement.SearchActivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.materialmanagement.R
import com.example.materialmanagement.SearchActivity.RecyclerViewAdapter.CustomerRecyclerAdapter
import com.google.android.material.tabs.TabItem


class SearchCustomer : AppCompatActivity() {
    private lateinit var refreshBtn : TabItem
    private lateinit var searchText : TextView

    private lateinit var orderBasic : Button

    private lateinit var customerRecyclerAdapter: CustomerRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_customer)

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
            val intent = Intent()
            intent.putExtra("searchResult", itemNumber.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        refreshBtn.setOnClickListener {
            Toast.makeText(this, "refresh", Toast.LENGTH_SHORT).show()
        }

        customerRecyclerAdapter = CustomerRecyclerAdapter()

        this.findViewById<RecyclerView>(R.id.in_num_list).apply {
            layoutManager =
                LinearLayoutManager(this@SearchCustomer, LinearLayoutManager.VERTICAL,false)
            adapter = customerRecyclerAdapter
        }
    }
}