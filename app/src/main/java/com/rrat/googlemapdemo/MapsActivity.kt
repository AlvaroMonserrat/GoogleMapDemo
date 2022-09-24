package com.rrat.googlemapdemo

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.rrat.googlemapdemo.databinding.ActivityMapsBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private val overlays by lazy { Overlays() }

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
            .tilt(0f)
            .build()


        // Add a marker in Sydney and move the camera
        val marker = mMap.addMarker(MarkerOptions()
            .position(santiago)
            .title("Santiago city")
            .snippet("Some random text")
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)))


        if (marker != null) {
            marker.tag = "Restaurant"
        }
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraSantiago))

        mMap.uiSettings.isZoomControlsEnabled = true
       // mMap.uiSettings.isZoomGesturesEnabled = false
       // mMap.uiSettings.isScrollGesturesEnabled = false

        //setMapStyle()
        //mMap.setPadding(0, 0, 300, 0)
        //onMapClicked()
        //onMapLongClicked()
        //animationCoroutine()

        //mMap.setOnMarkerClickListener(this)
        //addPolyline()

        //overlays.addGroundOverlay(mMap)

        checkLocationPermission()

    }

    @SuppressLint("MissingPermission")
    private fun checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        == PackageManager.PERMISSION_GRANTED){
            mMap.isMyLocationEnabled = true
            Toast.makeText(this, "Already Enabled", Toast.LENGTH_SHORT).show()
        }else{
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
            1
        )
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode != 1){
            return
        }
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Granted!", Toast.LENGTH_SHORT).show()
            mMap.isMyLocationEnabled = true
        }else{
            Toast.makeText(this, "We need your permission!", Toast.LENGTH_SHORT).show()

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

    private fun addPolyline(){
        val polyline = mMap.addPolyline(
            PolylineOptions().apply {
                add(
                LatLng(-33.44925052284258, -70.66758645726445),
                LatLng(-33.45228771631465, -70.70164191202672)
                )
                width(5f)
                color(Color.BLUE)
                geodesic(true)
            }
        )

    }

    private fun animationCoroutine(){

        val santiagoBounds = LatLngBounds(
            LatLng(-33.6807085094817, -70.80290284886097),
            LatLng(-33.23846951688101, -70.41700810809935)
        )

        lifecycleScope.launch {
            delay(5000)
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(santiagoBounds.center, 10f), 2000,
                object: GoogleMap.CancelableCallback{
                    override fun onCancel() {
                        TODO("Not yet implemented")
                    }

                    override fun onFinish() {
                        TODO("Not yet implemented")
                    }

                }
            )
            mMap.setLatLngBoundsForCameraTarget(santiagoBounds)
        }
    }

    private fun onMapClicked(){
        mMap.setOnMapClickListener {
            Toast.makeText(this, "Single Click map", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onMapLongClicked(){
        mMap.setOnMapLongClickListener {
            Toast.makeText(this, "Long Click map ${it.longitude} and ${it.latitude}", Toast.LENGTH_SHORT).show()
            mMap.addMarker(MarkerOptions().position(it).title("new marker"))
        }
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

    override fun onMarkerClick(marker: Marker): Boolean {
        Toast.makeText(this, "Marler ${marker.tag}", Toast.LENGTH_SHORT).show()
        marker.showInfoWindow()
        return true
    }
}