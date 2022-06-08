package com.example.map_k0.ui.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.map_k0.R
import com.example.map_k0.databinding.ActivityMainBinding
import com.example.map_k0.ui.view.base.BaseActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.navigation.NavigationView

class MainActivity : BaseActivity(){

    private var binding: ActivityMainBinding? = null
    private val navController by lazy { getActivityNavController() }
    private val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(
                R.id.mapFragment,
                R.id.eventsFragment,
                R.id.savedLocationsFragment,
            ), getDrawerLayout()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

   override fun onSupportNavigateUp(): Boolean {
       return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun getAppBarConfigurationActivity(): AppBarConfiguration =
        appBarConfiguration

    override fun getActionBarBase(): ActionBar? = supportActionBar

    override fun getNavDrawer() : NavigationView? = binding?.activityMainDrawerStart

    override fun getDrawerLayout() : DrawerLayout? = binding?.root

    private fun getActivityNavController(): NavController =
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController

}