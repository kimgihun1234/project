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

        //onButtonClick(view = null)
    }

    fun onButtonClick(view: View?) {
        val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()

        val json = JSONObject()
        //1. Login
        //json.put("id", "lshgooes3") // String
        //json.put("pw", "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3") // String
        //-> 반환값 String data

        //3. barcode
        //json.put("barcode", "10001a1")

        //5. stringInsert
        //json.put("cust_cd", "10001")
        //json.put("stor_cd", "101")
        //json.put("loca_cd", "101")
        //json.put("item_cd", "10002")
        //json.put("qty", 5)

        //6.1 storingDelete
        //val gson = GsonBuilder().setPrettyPrinting().create()
        //val data1 : StoringDelete = StoringDelete(20211100012, "10002", 1)
        //val data2 : StoringDelete = StoringDelete(20211100012, "10002", 1)
        //val storingDelets : List<StoringDelete> = Arrays.asList(data1, data2)
        //val jsonStirng = gson.toJson(storingDelets)
        //System.out.println(jsonStirng)

        //6.2
        val gson = GsonBuilder().setPrettyPrinting().create()
        val data1 : StoringDelete = StoringDelete("20211100012", "10002", 1)
        val data2 : StoringDelete = StoringDelete("20211100012", "10002", 2)
        val storingDelets : List<StoringDelete> = Arrays.asList(data1, data2)
        val jsonStirng = gson.toJson(storingDelets)
        System.out.println(jsonStirng)

        System.out.println(json.toString());
        val client = OkHttpClient()

        // ip 변수로 빼기

        //1. login post
        //val url = "http://172.30.1.30:8080/login"
        //<- String id String pw
        //-> String data = token

        //2. orderList get
        //val url = "http://172.30.1.30:8080/orderList"
        //-> String 3개 plord_no, cust_nm, cust_cd

        //3. barcode post
        //val url = "http://172.30.1.30:8080/barcode"
        //<- String barcode
        //-> String item_nm String item_cd Double qty

        //4. itemList get
        //val url = "http://172.30.1.30:8080/itemList"
        //-> String item_cd item_nm

        //4-1. item Current List get  나중에ㅔㅔㅔㅔㅔ
        //val url = "http://172.30.1.30:8080/curItemList"
        //-> Strin gitem_cd String item_nm String stan String comm_cd_nm String corp_cd busi_cd Double qty

        //4.5 location get
        //val url = "http://172.30.1.30:8080/locationList"
        //-> String stor_cd stor_nm loca_cd loca_nm

        //5. storingInsert post 입고시에만 jwt
        //val url = "http://172.30.1.30:8080/storingInsert?jwt=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzMzMzMzMzMy8xMDAxLzEwMDEiLCJleHAiOjE2MzY1NzkxMTV9.dWYyCtUDfxQcUYaNOcXWD5G4GqK1HkIhh58bzgF4rO4"
        //<- String cust_cd, stor_cd loca_cd item_cd Double qty
        //-> String purc_in_no
        // String data login에 받은거 token param으로 넣어줘야함


        //6. storingDelete post
        val url = "http://172.30.1.30:8080/storingDelete"
        //-> true만 올 것이다 성공하면 true 실패하면 false
        //<- String no String item_cd Double qty

        //val formBody: RequestBody = RequestBody.create(JSON, json.toString())
        val formBody: RequestBody = RequestBody.create(JSON, jsonStirng)

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
                    runOnUiThread { Log.d("testRequest", myRequest) } //여기서 원래 프레그먼트에 바인딩 - 넣어주기

                    //val jsonData = Gson().toJson(data);
                    //System.out.println(jsonData);

                }
            }
        })
    }
}

data class A(
    val a : Long,
    val b : String,
    val c : List<String>
)

data class StoringDelete(
    val no: String,
    val item_cd: String,
    val qty: Int
)