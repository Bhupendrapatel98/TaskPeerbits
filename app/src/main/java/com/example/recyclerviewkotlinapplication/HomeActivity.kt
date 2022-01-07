package com.example.recyclerviewkotlinapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.recyclerviewkotlinapplication.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    lateinit var navController: NavController
    lateinit var binding: ActivityHomeBinding
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
         navController = navHostFragment.navController

        //bottom nav
        binding.bottomNavView.setupWithNavController(navController)

        //drawer
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.home_fragment, R.id.detail_fragment, R.id.setting_fragment),
            binding.drawerLayout
        )

        //menu item click handle
        binding.navView.setupWithNavController(navController)

        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    //open drawer when drawer icon clicked and back btn presse
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).
        navigateUp(appBarConfiguration)
    }

    //bottom nav
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) ||
                super.onOptionsItemSelected(item)
    }
}