package com.tolulope.onlineschool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {
    private var edtEmailLogin : EditText? = null
    private var edtPwordLogin: EditText? = null
    private var btnLogin : Button? = null
    private var firebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        edtEmailLogin = findViewById(R.id.edtEmailLogin)
        edtPwordLogin = findViewById(R.id.edtPwordLogin)
        btnLogin = findViewById(R.id.btnLogin)
        firebaseAuth = FirebaseAuth.getInstance()

        btnLogin?.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser(){
        val email = edtEmailLogin?.text.toString().trim()
        val pWord = edtPwordLogin?.text.toString().trim()

        if (TextUtils.isEmpty(email)){
            Toast.makeText(applicationContext,"Please Enter An Email Address",Toast.LENGTH_SHORT).show()
        }
        else if (TextUtils.isEmpty(pWord)){
            Toast.makeText(applicationContext,"Please Enter a Password",Toast.LENGTH_SHORT).show()
        }
        else{
            firebaseAuth?.signInWithEmailAndPassword(email, pWord)?.addOnCompleteListener(object : OnCompleteListener<AuthResult>{
                override fun onComplete(p0: Task<AuthResult>) {
                    if (p0.isSuccessful){

                        val user: FirebaseUser = firebaseAuth?.currentUser!!
                        if (user.isEmailVerified){
                            Toast.makeText(applicationContext, "User logged in successfully", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
                        }
                        else{
                            Toast.makeText(applicationContext, "Account not verified", Toast.LENGTH_SHORT).show()
                        }
                    } else{
                        val error = p0.exception?.message
                        Toast.makeText(applicationContext, "Error: $error", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }



    }
}