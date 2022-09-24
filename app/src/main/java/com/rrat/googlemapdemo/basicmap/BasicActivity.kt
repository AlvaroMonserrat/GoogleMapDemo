package com.rrat.googlemapdemo.basicmap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rrat.googlemapdemo.ui.theme.GoogleMapDemoTheme

class BasicActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoogleMapDemoTheme {
                BasicMapScreen()
            }
        }
    }
}

