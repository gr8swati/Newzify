package com.example.newzify.activities

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.newzify.fragments.FavoritesFragment
import com.example.newzify.fragments.HomeViewFragment
import com.example.newzify.fragments.DownloadFragment
import com.example.newzify.R
import com.example.newzify.fragments.BussinessFragment
import com.example.newzify.fragments.PoliticsFragment
import com.example.newzify.fragments.ProfileFragment
import com.example.newzify.fragments.SettingsFragment
import com.example.newzify.fragments.SportsFragment
import com.example.newzify.fragments.TechnologyFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        var chipPolitics = findViewById<Chip>(R.id.politics_chip)
        var chipTechnologies = findViewById<Chip>(R.id.technologies_chip)
        var chipSports = findViewById<Chip>(R.id.sports_chip)
        var chipBusiness = findViewById<Chip>(R.id.bussiness_chip)

        val toolbar: MaterialToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Load the home view by default
        if (savedInstanceState == null) {
            loadHomeView()
        }

        chipPolitics.setOnClickListener{
            loadFragment(PoliticsFragment())
        }
        chipTechnologies.setOnClickListener{
            loadFragment(TechnologyFragment())
        }
        chipSports.setOnClickListener{
            loadFragment(SportsFragment())
        }
        chipBusiness.setOnClickListener{
            loadFragment(BussinessFragment())
        }

        // Bottom Navigation handling
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    loadHomeView() // Load Home view
                    true
                }
                R.id.nav_favorites -> {
                    loadFragment(FavoritesFragment())
                    true
                }
                R.id.nav_profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                R.id.nav_download -> {
                    loadFragment(DownloadFragment())
                    true
                }
                else -> false
            }
        }
    }

    // Function to load the home view (FrameLayout)
    private fun loadHomeView() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.news_feed_frame, HomeViewFragment()) // A fragment that displays the home layout
            .commit()
    }

    // Function to load a fragment
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.news_feed_frame, fragment) // Replace the FrameLayout with the selected fragment
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as? SearchView

        if (searchView != null) {
            val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
            searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
        return true
    }




}
