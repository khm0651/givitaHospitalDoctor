package com.example.vitameanshospitaldoctor

import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(msg: String, time: Int){
    val snack = Snackbar.make(this,msg,time)
    val view = snack.view
    var tv = view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
    tv.gravity = Gravity.CENTER
    tv.textAlignment = View.TEXT_ALIGNMENT_CENTER
    snack.show()
}