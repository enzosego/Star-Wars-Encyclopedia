package com.example.starwarsencyclopedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.starwarsencyclopedia.model.CharacterViewModel
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private val viewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }

    override fun onBackPressed() {
        with (viewModel) {
            if (isUserSearching.value!!) {
                refreshPage()
                viewModel.switchSearchingStatus(false)
                findViewById<TextInputEditText>(R.id.searchInput)
                    .setText("")
            } else if (currentPageNum.value!! > 0 && !isDescriptionDisplayed.value!!) {
                pageDown()
            } else
                super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}