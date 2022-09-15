package com.rrat.googlemapdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.rrat.googlemapdemo.ui.theme.GoogleMapDemoTheme

class MapsComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoogleMapDemoTheme {
                val singapore = LatLng(1.35, 103.87)
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(singapore, 10f)
                }
                Box(Modifier.fillMaxSize()) {
                    GoogleMap(cameraPositionState = cameraPositionState)
                    Button(onClick = {
                        // Move the camera to a new zoom level
                        cameraPositionState.move(CameraUpdateFactory.zoomIn())
                    }) {
                        Text(text = "Zoom In")
                    }
                }
            }
        }
    }
}

