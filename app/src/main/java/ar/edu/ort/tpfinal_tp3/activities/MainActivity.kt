package ar.edu.ort.tpfinal_tp3.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ar.edu.ort.tpfinal_tp3.R
import ar.edu.ort.tpfinal_tp3.utils.UserSession
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initSession()
    }


    private fun initSession() {

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
