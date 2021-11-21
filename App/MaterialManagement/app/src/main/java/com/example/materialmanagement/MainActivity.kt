package com.example.materialmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.example.materialmanagement.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.IOException
import java.util.*
import com.google.gson.GsonBuilder

import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.view.*


class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding

    private val tabName = listOf(
        "입출고", "반품", "현황보기", "설정"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        onButtonClick(view = null)
    }

    fun onButtonClick(view: View?) {
        // ip 변수로 빼기

        //1. login post
        //val url = "http://101.101.208.223:8080/login"
        //<- String id String pw
        //-> String data = token

        //5. storingInsert post 입고시에만 jwt
        //val url = "http://101.101.208.223:8080/storingInsert?jwt=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzMzMzMzMzMy8xMDAxLzEwMDEiLCJleHAiOjE2MzY1NzkxMTV9.dWYyCtUDfxQcUYaNOcXWD5G4GqK1HkIhh58bzgF4rO4"
        //<- String cust_cd, stor_cd loca_cd item_cd Double qty
        //-> String purc_in_no
        // String data login에 받은거 token param으로 넣어줘야함


        //6. storingDelete post
        //val url = "http://101.101.208.223:8080/storingDelete"
        //-> true만 올 것이다 성공하면 true 실패하면 false
        //<- String no String item_cd Double qty

        //val formBody: RequestBody = RequestBody.create(JSON, json.toString())
        //val formBody: RequestBody = RequestBody.create(JSON, jsonStirng)

//        val request: Request = Request.Builder()
//            .url(url)
//            .get()
//            .build();
//
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                runOnUiThread{ Log.d("test","failt")}
//            }
//
//            @Throws(IOException::class)
//            override fun onResponse(call: Call, response: Response) {
//                if (response.isSuccessful) {
//                    val myRequest = response.body!!.string()
//                    //val data = Gson().fromJson<A>(myRequest, A::class.java);
//                    //System.out.println(data.a.toString() + ", " +  data.b + ", " + data.c);
//                    runOnUiThread { Log.d("testRequest", myRequest) } //여기서 원래 프레그먼트에 바인딩 - 넣어주기
//
//                    //val jsonData = Gson().toJson(data);
//                    //System.out.println(jsonData);
//
//                }
//            }
//        })
    }
}