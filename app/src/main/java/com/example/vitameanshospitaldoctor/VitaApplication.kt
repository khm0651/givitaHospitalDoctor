package com.example.vitameanshospitaldoctor

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class VitaApplication: Application() {
    override fun onCreate() {
        super.onCreate()

    }
}