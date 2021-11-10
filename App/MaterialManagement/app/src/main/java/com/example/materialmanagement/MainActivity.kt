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
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
//    private val tabIcon = listOf(
//        R.drawable ~~어쩌고,
//        R.drawable ~~어쩌고
//    )

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
            setPageTransformer(ZoomOutPageTransformer())
        }

        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            tab.setText(tabName[position])
            //tab.text = "Title $position"
            //tab.setIcon(tabIcon[position]) // 배열 순서대로
        }.attach()

        onButtonClick(view = null)
    }

    fun onButtonClick(view: View?) {
        val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()

        val json = JSONObject()
        json.put("id", "lshgooes3")
        json.put("pw", "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3")

        System.out.println(json.toString());
        val client = OkHttpClient()
        //val url = "https://www.naver.com";
        //val url = "http://192.168.0.29:8090/test2?subject=1234"
        val url = "http://172.30.1.30:8080/login"
        val formBody: RequestBody = RequestBody.create(JSON, json.toString());

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
                    val myRequest = response.body!!.string()
                    //val data = Gson().fromJson<A>(myRequest, A::class.java);
                    //System.out.println(data.a.toString() + ", " +  data.b + ", " + data.c);
                    runOnUiThread { Log.d("testRequest", myRequest) }

                    //val jsonData = Gson().toJson(data);
                    //System.out.println(jsonData);

                }
            }
        })


    }
}

data class A(
    val a : Int,
    val b : String,
    val c : List<String>
)