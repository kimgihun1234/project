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

    private val tabName = listOf(
        "입출고", "반품", "현황보기", "설정"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val extras = intent.extras

        if (extras != null) {
            jwt = extras.getString("jwt")!! // 값 꺼내기
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

    fun onButtonClick(view: View?) {

        //6. storingDelete post
        //val url = "http://101.101.208.223:8080/storingDelete"
        //-> true만 올 것이다 성공하면 true 실패하면 false
        //<- String no String item_cd Double qty
    }

    fun getJwt(): String {
        return jwt
    }
}