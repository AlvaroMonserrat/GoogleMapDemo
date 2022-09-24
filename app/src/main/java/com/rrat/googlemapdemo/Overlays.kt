package com.rrat.googlemapdemo

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.GroundOverlayOptions
import com.google.android.gms.maps.model.LatLng

class Overlays{

    val santiago = LatLng(-33.44925052284258, -70.66758645726445)

    fun addGroundOverlay(map: GoogleMap)
    {
          val groundOverlay = map.addGroundOverlay(
              GroundOverlayOptions().apply {
                  position(santiago, 1000f, 1000f)
              }
          )
    }
}