package com.example.materialmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.widget.Button
import com.example.materialmanagement.MainActivity
import com.example.materialmanagement.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun login(v : View)
    {
        if(edit_Id.text.toString()== "knu1234" && edit_Password.text.toString() =="1234")
        {
            Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        else
        {
            edit_Password.text = null
            Toast.makeText(this, "아이디나 비번이 틀렸습니다.", Toast.LENGTH_SHORT).show()
        }
    }

}
