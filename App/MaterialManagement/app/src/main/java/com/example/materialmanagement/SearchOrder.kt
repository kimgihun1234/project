package com.example.materialmanagement

import android.R.attr
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableRow
import android.widget.TextView
import android.R.attr.data
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.tabs.TabItem


class SearchOrder : AppCompatActivity() {
    private lateinit var refreshBtn : TabItem
    private lateinit var searchText : TextView
    private lateinit var row1 : TableRow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_order)

        searchText = findViewById(R.id.searchText)
        refreshBtn = findViewById(R.id.refreshBtn)

        val extras = intent.extras
        val itemNumber: String?

        if (extras != null) {
            itemNumber = extras.getString("query")
            searchText.setText(itemNumber)
        }

        refreshBtn.setOnClickListener{
            Toast.makeText(this, "refresh", Toast.LENGTH_SHORT).show()
        }

        row1 = findViewById(R.id.row1)

        row1.setOnClickListener{
            Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show()
        }
    }
}