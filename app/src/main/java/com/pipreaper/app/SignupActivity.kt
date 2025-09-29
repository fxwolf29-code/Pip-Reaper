package com.pipreaper.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val emailField = findViewById<EditText>(R.id.email)
        val passwordField = findViewById<EditText>(R.id.password)
        val btnCreate = findViewById<Button>(R.id.btnCreate)
        btnCreate.setOnClickListener {
            val email = emailField.text.toString().trim()
            val pass = passwordField.text.toString().trim()
            if (email.isEmpty() || pass.length < 4) {
                Toast.makeText(this, "Enter valid details (password >=4)", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // In a real app, you'd create an account on server. Here we mock and proceed.
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("user_email", email)
            startActivity(intent)
            finish()
        }
    }
}
