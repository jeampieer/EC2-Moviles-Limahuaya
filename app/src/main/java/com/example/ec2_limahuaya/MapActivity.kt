package com.example.ec2_limahuaya

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ec2_limahuaya.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Agregar marcadores
        addMarker(LatLng(37.7749, -122.4194), "San Francisco", "California")
        addMarker(LatLng(51.5074, -0.1278), "Londres", "Reino Unido")
        addMarker(LatLng(35.6895, 139.6917), "Tokio", "Japón")

        // Establecer posición y nivel de zoom
        val initialPosition = LatLng(37.7749, -122.4194)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(initialPosition, 10f))
    }

    private fun addMarker(position: LatLng, title: String, snippet: String) {
        map.addMarker(MarkerOptions().position(position).title(title).snippet(snippet))
    }
}
