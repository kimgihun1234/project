package com.example.materialmanagement.SplashScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.materialmanagement.MainActivity
import com.example.materialmanagement.R

class SplashActivity : AppCompatActivity() {
    private val DURATION : Long = 1900 //1초 = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, DURATION)
    }
}