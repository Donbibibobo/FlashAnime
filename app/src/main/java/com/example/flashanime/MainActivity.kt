package com.example.flashanime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.flashanime.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this


        setupBottomNav()
    }

    private fun setupBottomNav() {
        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {

                    findNavController(R.id.myNavHostFragment).navigate(NavigationDirections.navigateToHomeFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_all -> {

                    findNavController(R.id.myNavHostFragment).navigate(NavigationDirections.navigateToAllFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_collected -> {

                    findNavController(R.id.myNavHostFragment).navigate(NavigationDirections.navigateToCollectedFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_vocabulary -> {

                    findNavController(R.id.myNavHostFragment).navigate(NavigationDirections.navigateToVocabularyFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_profile -> {

                    findNavController(R.id.myNavHostFragment).navigate(NavigationDirections.navigateToProfileFragment())
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }


}