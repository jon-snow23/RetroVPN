package com.shiva.retrovpn.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.shiva.retrovpn.MainActivity
import com.shiva.retrovpn.databinding.ActivitySplashBinding


class SplashActivity : AppCompatActivity() {

    private var binding: ActivitySplashBinding?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        startMainActivity()
    }

    private fun startMainActivity() {
        Handler(Looper.myLooper()!!).postDelayed({
            startActivity(Intent(this@SplashActivity, ControllerActivity::class.java))
            finish()
        }, 2000)
    }

}