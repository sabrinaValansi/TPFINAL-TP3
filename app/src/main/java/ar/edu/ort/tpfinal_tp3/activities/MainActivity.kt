package ar.edu.ort.tpfinal_tp3.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import ar.edu.ort.tpfinal_tp3.R
import ar.edu.ort.tpfinal_tp3.utils.UserSession
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfig : AppBarConfiguration
    private lateinit var navView : NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initSessionAndHamgMenu()
    }


    private fun initSessionAndHamgMenu() {

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        navView = findViewById(R.id.nav_view)

        navView.setupWithNavController(navController)


        appBarConfig = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.home2,
                R.id.settingsActivity,
                R.id.login
            ),
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )

        setupActionBarWithNavController(navController, appBarConfig)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment


        // Agrego un listener para poder escuchar cada vez que se realiza una navegacion
        navHostFragment.navController.addOnDestinationChangedListener { _, destination, arguments ->
            // Si mi destino es la Home, tomo el userName que recibio por parametro y lo almaceno en un Object
            if (destination.id == R.id.home2) {
                arguments?.getString("userName")?.let { UserSession.userName = it }
            }
        }
    }


}
