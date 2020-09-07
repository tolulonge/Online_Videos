package com.tolulope.onlineschool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class RegOrLog : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_or_log)


    }

    fun registerUser(view: View) {
        startActivity(Intent(this@RegOrLog, RegisterActivity::class.java))
    }
    fun loginUser(view: View) {
        startActivity(Intent(this@RegOrLog, LoginActivity::class.java))
    }
}