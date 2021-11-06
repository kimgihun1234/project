package com.example.materialmanagement.SearchActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.materialmanagement.R
import com.example.materialmanagement.SearchActivity.RecyclerViewAdapter.OutRecyclerAdapter
import com.google.android.material.tabs.TabItem


class SearchOutOrder : AppCompatActivity() {
    private lateinit var refreshBtn : TabItem
    private lateinit var searchText : TextView

    private lateinit var outNumRecyclerAdapter: OutRecyclerAdapter // adapter 수정 필요, 아래도

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_out_order)

        searchText = findViewById(R.id.searchText)
        refreshBtn = findViewById(R.id.refreshBtn)

        val extras = intent.extras
        val itemNumber: String?

        if (extras != null) {
            itemNumber = extras.getString("query")
            searchText.setText(itemNumber)
        }

        refreshBtn.setOnClickListener {
            Toast.makeText(this, "refresh", Toast.LENGTH_SHORT).show()
        }

        outNumRecyclerAdapter = OutRecyclerAdapter()

        this.findViewById<RecyclerView>(R.id.in_num_list).apply {
            layoutManager =
                LinearLayoutManager(this@SearchOutOrder, LinearLayoutManager.VERTICAL,false)
            adapter = outNumRecyclerAdapter
        }


    }
}