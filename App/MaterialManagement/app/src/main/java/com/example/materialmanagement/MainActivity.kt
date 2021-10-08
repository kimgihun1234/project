package com.example.materialmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.example.materialmanagement.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
//    private val tabIcon = listOf(
//        R.drawable ~~어쩌고,
//        R.drawable ~~어쩌고
//    )

    private val tabName = listOf(
        "홈", "입출고", "기록", "현황보기"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewpager.apply {
            adapter = MyPagerAdapter(context as FragmentActivity)
            setPageTransformer(ZoomOutPageTransformer())
        }

        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            tab.setText(tabName[position])
            //tab.text = "Title $position"
            //tab.setIcon(tabIcon[position]) // 배열 순서대로
        }.attach()
    }
}