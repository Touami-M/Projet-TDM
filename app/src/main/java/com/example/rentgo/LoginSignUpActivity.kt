package com.example.rentgo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController


class LoginSignUpActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_signup_activity)
        /*val navHostFragment = supportFragmentManager. findFragmentById(R.id.navHost) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)*/

    }
}


