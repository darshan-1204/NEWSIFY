package com.example.newsify.Activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.newsify.SQLDatabase.SqliteDatabase
import com.example.newsify.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var db: SqliteDatabase
    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = getSharedPreferences("MyPrefs", 0)

        if (isLoggedIn()) {
            startMainActivity()
        }

        db = SqliteDatabase(this)
        var list = db.showData()

        binding.tvSignUp.setOnClickListener {
            startActivity(Intent(this,SignupActivity::class.java))
        }
        binding.btnSignIn.setOnClickListener {

            var email = binding.edtEmail.text.toString()
            var pass = binding.edtPass.text.toString()

            var flag = false

            for (i in 0 until list.size) {
                if (list[i].email == email && list[i].password == pass) {
                    flag = true
                    break
                }
            }

            if (flag) {
                saveLoginState(true)
                Toast.makeText(this@LoginActivity, "Login Success", Toast.LENGTH_SHORT).show()
                startMainActivity()
                finish()
            } else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun isLoggedIn(): Boolean {
        return preferences.getBoolean("isLoggedIn", false)
    }

    private fun saveLoginState(isLoggedIn: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean("isLoggedIn", isLoggedIn)
        editor.apply()
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}