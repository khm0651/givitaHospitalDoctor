package com.example.vitameanshospitaldoctor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vitameanshospitaldoctor.utils.Util

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Util.init(applicationContext)
    }
}