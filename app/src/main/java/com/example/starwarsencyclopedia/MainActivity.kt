package com.example.starwarsencyclopedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.starwarsencyclopedia.model.CharacterViewModel

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.search_toolbar, menu)

        val menuItem = menu.findItem(R.id.action_search)

        val searchView = menuItem.actionView as SearchView
        searchView.queryHint = "Anakin Skywalker"
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.filterCharacters(newText)
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        with (viewModel) {
            if (isUserSearching.value!!) {
                refreshList()
                switchSearchingStatus(false)
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