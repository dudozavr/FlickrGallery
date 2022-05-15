package com.dudnyk.framework.flickrgallery.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dudnyk.framework.flickrgallery.R
import com.dudnyk.framework.flickrgallery.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var toolbar: Toolbar
    private lateinit var bottomNavBar: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initFields()
        setupNavController()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.toolbar_home, menu)
//        return true
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initFields() {
        toolbar = binding.toolbar
        bottomNavBar = binding.bottomNav
    }

    private fun setupNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(
            navController, AppBarConfiguration(
                setOf(
                    R.id.home_fragment,
                    R.id.search_fragment
                )
            )
        )
        bottomNavBar.setupWithNavController(navController)
    }
}