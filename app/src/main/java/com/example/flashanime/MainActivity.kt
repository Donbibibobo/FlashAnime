package com.example.flashanime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.flashanime.databinding.ActivityMainBinding
import com.example.flashanime.ext.getVmFactory
import com.example.flashanime.util.CurrentFragmentType
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView

class MainActivity : AppCompatActivity() {

    val viewModel by viewModels<MainViewModel> { getVmFactory() }

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        // set BottomNavigation
        viewModel.setBottomNavigation(binding.bottomNavView)

        // observe current fragment change, only for show info
        viewModel.currentFragmentType.observe(
            this,
            Observer {
                Log.i("MainAA", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
                Log.i("MainAA", "[${viewModel.currentFragmentType.value}]")
                Log.i("MainAA","~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
            }
        )

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.back.setOnClickListener {
            navController.navigateUp()
        }

        setupNavController()
        setupBottomNav()
    }

    private fun setupBottomNav() {
        binding.bottomNavView.setOnItemSelectedListener { item ->
            Log.i("itemIddd","${item.itemId}")
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

                    when(viewModel.checkIsLogin()){
                        true -> {
                            findNavController(R.id.myNavHostFragment).navigate(NavigationDirections.navigateToCollectedFragment(false))
                            return@setOnItemSelectedListener true
                        }
                        false -> {
                            findNavController(R.id.myNavHostFragment).navigate(NavigationDirections.navigateToLoginDialog())
                            return@setOnItemSelectedListener true
                        }
                    }
                }
                R.id.navigation_vocabulary -> {

                    when(viewModel.checkIsLogin()){
                        true -> {
                            findNavController(R.id.myNavHostFragment).navigate(NavigationDirections.navigateToVocabularyFragment())
                            return@setOnItemSelectedListener true
                        }
                        false -> {
                            findNavController(R.id.myNavHostFragment).navigate(NavigationDirections.navigateToLoginDialog())
                            return@setOnItemSelectedListener true
                        }
                    }
                }
                R.id.navigation_profile -> {

                    when(viewModel.checkIsLogin()){
                        true -> {
                            findNavController(R.id.myNavHostFragment).navigate(NavigationDirections.navigateToProfileFragment())
                            return@setOnItemSelectedListener true
                        }
                        false -> {
                            findNavController(R.id.myNavHostFragment).navigate(NavigationDirections.navigateToLoginDialog())
                            return@setOnItemSelectedListener true
                        }
                    }
                }
            }
            false
        }
    }

    /**
     * Set up [NavController.addOnDestinationChangedListener] to record the current fragment
     * which is change the [CurrentFragmentType] enum value by [MainViewModel] at [onCreateView]
     */
    private fun setupNavController() {
        findNavController(R.id.myNavHostFragment).addOnDestinationChangedListener { navController: NavController, _: NavDestination, _: Bundle? ->
            viewModel.currentFragmentType.value = when (navController.currentDestination?.id) {
                R.id.homeFragment -> CurrentFragmentType.HOME
                R.id.allFragment -> CurrentFragmentType.ALL
                R.id.collectedFragment -> {
                    val fromProfile = navController.currentBackStackEntry?.arguments?.getBoolean("fromProfile") ?: false
                    if (fromProfile) {
                        CurrentFragmentType.PROFILE_COLLECTED
                    } else {
                        CurrentFragmentType.COLLECTED
                    }
                }
                R.id.vocabularyFragment -> CurrentFragmentType.VOCABULARY
                R.id.profileFragment -> CurrentFragmentType.PROFILE
                R.id.detailFragment -> CurrentFragmentType.DETAIL

                R.id.vocabularyDetailFragment -> CurrentFragmentType.VOCABULARY_DETAIL
                R.id.wordTestFragment -> CurrentFragmentType.WORD_TEST

                R.id.watchHistoryFragment -> CurrentFragmentType.WATCH_HISTORY
                R.id.wordsCollectionFragment -> CurrentFragmentType.WORDS_COLLECTION

                else -> viewModel.currentFragmentType.value
            }
        }
    }


}