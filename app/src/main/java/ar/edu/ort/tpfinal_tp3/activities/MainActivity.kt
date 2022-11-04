package ar.edu.ort.tpfinal_tp3.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initSessionAndHamgMenu()

        sharedPreferences = getSharedPreferences("Mode", Context.MODE_PRIVATE)

        val nightMode : Boolean = sharedPreferences.getBoolean("night", false)

        if (nightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
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

                if (destination.id == R.id.home2) {
                    arguments?.getString("userName")?.let { UserSession.userName = it }
                }
            } else if (destination.id == R.id.detail) {
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


}
