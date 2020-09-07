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

class RegisterActivity : AppCompatActivity() {

    private var edtEmailSignUp : EditText? = null
    private var edtPwSignUp : EditText? = null
    private var btnRegister: Button? = null
    private var firebaseAuth : FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        edtEmailSignUp = findViewById(R.id.edtEmailSignUp)
        edtPwSignUp = findViewById(R.id.edtPwordSignUp)
        btnRegister = findViewById(R.id.btnRegister)
        firebaseAuth = FirebaseAuth.getInstance()


        btnRegister?.setOnClickListener {
            registerNewUser()
        }
    }

    private fun registerNewUser(){
        val emailSignUp = edtEmailSignUp?.text.toString().trim()
        val pWordSignUp = edtPwSignUp?.text.toString().trim()

        if (TextUtils.isEmpty(emailSignUp)){
            Toast.makeText(applicationContext, "Please Enter an Email Address", Toast.LENGTH_SHORT).show()
        }
        else if (TextUtils.isEmpty(pWordSignUp)){
            Toast.makeText(applicationContext, "Please Enter a Password", Toast.LENGTH_SHORT).show()
        }
        else {
            firebaseAuth?.createUserWithEmailAndPassword(emailSignUp, pWordSignUp)?.addOnCompleteListener(object : OnCompleteListener<AuthResult>{
                override fun onComplete(p0: Task<AuthResult>) {
                    if(p0.isSuccessful){
                        Toast.makeText(applicationContext, "Account Created",Toast.LENGTH_SHORT ).show()
                        val user: FirebaseUser = firebaseAuth!!.currentUser!!
                        user.sendEmailVerification().addOnCompleteListener(object : OnCompleteListener<Void>{
                            override fun onComplete(p0: Task<Void>) {
                                if (p0.isSuccessful){
                                    Toast.makeText(applicationContext, "Please Check Your Email for Verification Link", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                                }
                                else{
                                    Toast.makeText(applicationContext, "Error: ${p0.exception?.message}", Toast.LENGTH_SHORT).show()
                                }
                            }
                        })
                    }
                    else{
                        Toast.makeText(applicationContext, "Error: ${p0.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

    }
}