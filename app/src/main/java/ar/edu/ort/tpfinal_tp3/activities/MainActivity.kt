package ar.edu.ort.tpfinal_tp3.activities

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import ar.edu.ort.tpfinal_tp3.R
import ar.edu.ort.tpfinal_tp3.utils.UserSession
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    lateinit var sharedPreferences: SharedPreferences
    private var isTheFragment : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initSessionAndHamgMenu()

        sharedPreferences = getSharedPreferences("Mode", Context.MODE_PRIVATE)

        val nightMode : Boolean = sharedPreferences.getBoolean("night", false)

        if (nightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#454545")))
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

    }

    private fun initSessionAndHamgMenu() {

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController

        navigationView.setupWithNavController(navController)

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        /*navController.addOnDestinationChangedListener{_, _, _, ->
            supportActionBar?.setHomeAsUpIndicator(R.drawable.hamburger_icon)
        }*/


        navController.addOnDestinationChangedListener { _, destination, arguments ->


            if(destination.id == R.id.home2) {
                supportActionBar?.show()
                supportActionBar?.setHomeAsUpIndicator(R.drawable.hamburger_icon)
                supportActionBar?.setDisplayShowTitleEnabled(false);
                isTheFragment = false

                arguments?.getString("userName")?.let { UserSession.userName = it }

            } else if (destination.id == R.id.detail) {
                isTheFragment = true
                supportActionBar?.show()
                supportActionBar?.setDisplayShowTitleEnabled(false);
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setDisplayShowHomeEnabled(true)

            }
            else  {
                supportActionBar?.hide()
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items

        if (isTheFragment) {
            when (item.itemId) {
                android.R.id.home -> {
                    this?.onBackPressed()
                    return true
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
