package com.example.materialmanagement

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.example.materialmanagement.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.view.*
import okhttp3.*
import java.util.*


class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private var jwt : String = "null"
    private var emp_nm : String = "null"
    private var id : String = "null"

    private val tabName = listOf(
        "입출고", "반품", "현황보기", "설정"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val extras = intent.extras

        if (extras != null) {
            jwt = extras.getString("jwt")!! // 값 꺼내기
            emp_nm = extras.getString("emp_nm")!!
            id = extras.getString("id")!!
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewpager.apply {
            adapter = MainPagerAdapter(context as FragmentActivity)
            viewpager.isUserInputEnabled = false
            //setPageTransformer(ZoomOutPageTransformer())
        }

        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            tab.setText(tabName[position])
        }.attach()
    }

    override fun onBackPressed(){}

    fun getJwt(): String {
        return jwt
    }

    fun getEmpName() : String {
        return emp_nm
    }

    fun getId() : String {
        return id
    }
}