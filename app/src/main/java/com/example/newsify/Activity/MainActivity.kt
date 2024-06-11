package com.example.newsify.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newsify.Fragment.HomeFragment
import com.example.newsify.Fragment.ProfileFragment
import com.example.newsify.Fragment.SearchFragment
import com.example.newsify.R
import com.example.newsify.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, HomeFragment())
            .commit()

        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, HomeFragment()).commit()

                R.id.menu_search -> supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, SearchFragment()).commit()

                R.id.menu_profile -> supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, ProfileFragment()).commit()
            }
            true
        }

    }
}