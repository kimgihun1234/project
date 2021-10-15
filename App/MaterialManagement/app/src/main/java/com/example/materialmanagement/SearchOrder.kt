package com.example.materialmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SearchOrder : AppCompatActivity() {
    private lateinit var testText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_order)
        testText = findViewById(R.id.testText)

        val extras = intent.extras
        val userName: String?

        if (extras != null) {
            userName = extras.getString("query")
            testText.setText(userName)
        }
    }
}