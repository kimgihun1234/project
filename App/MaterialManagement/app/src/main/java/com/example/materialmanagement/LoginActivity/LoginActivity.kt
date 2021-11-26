package com.example.materialmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.util.Log
import android.widget.Button
import com.example.materialmanagement.DTO.BarcodeInfo
import com.example.materialmanagement.DTO.BarcodePostInfo
import com.example.materialmanagement.DTO.LoginInfo
import com.example.materialmanagement.DTO.LoginPostInfo
import com.example.materialmanagement.MainActivity
import com.example.materialmanagement.R
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.IOException


class LoginActivity: AppCompatActivity() {
    private lateinit var myRequest: String
    private var data: List<LoginInfo> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val sharedPreference = getSharedPreferences("other", 0)
        edit_Id.setText(sharedPreference.getString("id", ""))
        edit_Password.setText(sharedPreference.getString("pw", ""))
        Check.isChecked = sharedPreference.getBoolean("auto_login", false)
    }

    fun login(v : View)
    {
        val id = edit_Id.text.toString()
        val pw = edit_Password.text.toString()
        val sharedPreference = getSharedPreferences("other", 0)
        val editor = sharedPreference.edit()
        val client = OkHttpClient()
        val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()
        val gson = GsonBuilder().setPrettyPrinting().create()
        //val id = "lshgooes3"
        //val pw = "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3"
        val postData = LoginPostInfo(id, pw)
        val jsonString = gson.toJson(postData)
        val formBody: RequestBody = RequestBody.create(JSON, jsonString)
        System.out.println(jsonString)

        val url = "http://101.101.208.223:8080/login"
        val request: Request = Request.Builder()
            .url(url)
            .post(formBody)
            .build();

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread { Log.d("test", "failt") }
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    myRequest = response.body!!.string()
                    if(myRequest != "null") {

                        val requestList = "[$myRequest]"
                        var loginSuccessful=false
                        data = Gson().fromJson(requestList, Array<LoginInfo>::class.java).toList()
                        runOnUiThread {
                            for(i in 0..data.size-1){
                                System.out.println(data[i].data)
                            }
                            if(data[0].data != "fail"){
                                loginSuccessful = true
                            }
                            if(loginSuccessful)
                            {
                                if(Check.isChecked) {
                                    editor.putString("id", id)
                                    editor.putString("pw", pw)
                                    editor.putBoolean("auto_login", true)
                                    editor.apply()
                                }
                                else {
                                    editor.putString("id", "")
                                    editor.putString("pw", "")
                                    editor.putBoolean("auto_login", false)
                                    editor.apply()
                                }
                                Toast.makeText(this@LoginActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                intent.putExtra("jwt", data[0].data)
                                intent.putExtra("emp_nm", data[0].emp_nm)
                                intent.putExtra("id", id)
                                startActivity(intent)
                            }
                            else {
                                editor.putString("id", "")
                                editor.putString("pw", "")
                                editor.putBoolean("auto_login", false)
                                editor.apply()
                                edit_Password.text = null
                                Toast.makeText(this@LoginActivity, "아이디나 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        editor.putString("id", "")
                        editor.putString("pw", "")
                        editor.putBoolean("auto_login", false)
                        editor.apply()
                        edit_Password.text = null
                        Toast.makeText(this@LoginActivity, "통신 실패했습니다", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}