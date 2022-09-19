package com.rrat.googlemapdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.rrat.googlemapdemo.databinding.ActivityMapsBinding
import kotlinx.coroutines.GlobalScope

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val santiago = LatLng(-33.44925052284258, -70.66758645726445)

        //Add Camera
        val cameraSantiago = CameraPosition.Builder()
            .target(santiago)
            .zoom(17f)
            .bearing(0f)
            .tilt(45f)
            .build()

        // Add a marker in Sydney and move the camera
        mMap.addMarker(MarkerOptions().position(santiago))
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraSantiago))

        //mMap.uiSettings.isZoomControlsEnabled = true
       // mMap.uiSettings.isZoomGesturesEnabled = false
       // mMap.uiSettings.isScrollGesturesEnabled = false

        //setMapStyle()
        //mMap.setPadding(0, 0, 300, 0)
    }

    private fun setMapStyle() {
        try {
            /*val success = mMap.setMapStyle(
                //MapStyleOptions.loadRawResourceStyle(this, R.raw.style)
            )
*/
        }catch (e: Exception){

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_styles, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.normal_map ->{
                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            }
            R.id.hybrid_map ->{
                mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            }
            R.id.satellite_map ->{
                mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            }
            R.id.terrain_map ->{
                mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
            }
        }

        return true
    }
}