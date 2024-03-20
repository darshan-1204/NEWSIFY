package com.example.newsify.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.newsify.SQLDatabase.SqliteDatabase
import com.example.newsify.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    var Gender = arrayOf("Male", "Female", "Other")
    var selectedGender: String = "Male"
    var countryList =
        arrayOf("India", "Usa", "England", "South Africa", "West Indies", "Australia", "China")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = SqliteDatabase(this)

        var genderAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Gender)

        binding.spinnerGender.adapter = genderAdapter

        binding.spinnerGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                selectedGender = Gender[i]
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {

            }
        }

//
//        var countryAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,countryList)
//        binding.spinnerCountry.adapter = countryAdapter
//
//
//        binding.spinnerCountry.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
//            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                Toast.makeText(this@SignupActivity, "" + countryList[p2], Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//
//            }
//
//        }

        binding.btnSignUp.setOnClickListener {

            if (binding.edtFirstname.text.isEmpty() || binding.edtEmail.text.isEmpty() || binding.edtPass.text.isEmpty() || binding.edtConfirmPass.text.isEmpty()) {
                Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show()
            } else {

                if (binding.edtPass.text.toString() != binding.edtConfirmPass.text.toString()) {
                    Toast.makeText(
                        this,
                        "password does not match",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {

                    db.AddData(
                        binding.edtFirstname.text.toString(),
                        binding.edtEmail.text.toString(),
                        selectedGender,
                        binding.edtPass.text.toString()
                    )

                    binding.edtFirstname.setText("")
                    binding.edtEmail.setText("")
                    binding.edtPass.setText("")
                    binding.edtConfirmPass.setText("")

                    Toast.makeText(this, "Sign up successfully", Toast.LENGTH_SHORT).show()
                }
            }

        }

        binding.tvSignIn.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}