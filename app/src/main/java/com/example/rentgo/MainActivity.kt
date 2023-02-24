package com.example.rentgo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.rentgo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val navHostFragment = supportFragmentManager. findFragmentById(R.id.navHost) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.navBottom,navController)

        navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->
            if (nd.id == R.id.carDetailsFragment  || nd.id == R.id.bookingFragment || nd.id == R.id.rentDetailsFragment) {
                binding.navBottom.visibility = View.GONE
            } else {
                binding.navBottom.visibility = View.VISIBLE
            }
        }
    }

    /*override fun onSupportNavigateUp() = navController.navigateUp() || super.onSupportNavigateUp()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.overflow_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.logout-> {
                val intent = Intent(this@MainActivity,LoginSignUpActivity::class.java)
                this.startActivity(intent)
                val pref = getSharedPreferences("users", Context.MODE_PRIVATE)
                pref.edit { putBoolean("connected", false)}
                finish()
            }
        }
        return super.onOptionsItemSelected(item)

    }*/

}