package com.example.map_k0

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.security.AccessController.getContext

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        //createFragment()
    }

    private fun createFragment(){
        val mapFragment:SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(mapaCreado: GoogleMap) {
        mapaCreado.apply{
            setMapStyle(MapStyleOptions.loadRawResourceStyle(this@MainActivity, R.raw.map_style));
            GoogleMapOptions().mapId("6c92c2dd989894a2");
            val coordenadas = LatLng(37.37410286896958, -5.969290673333865)
            addMarker(
                MarkerOptions()
                    .position(coordenadas)
                    .title("IES NERVION")
            )
        }
        map = mapaCreado
    }
}