package com.example.newsify.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.newsify.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Handler().postDelayed({
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 1500)

    }
}