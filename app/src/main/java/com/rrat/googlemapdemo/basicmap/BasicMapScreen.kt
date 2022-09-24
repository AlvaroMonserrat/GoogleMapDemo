package com.rrat.googlemapdemo.basicmap

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.google.maps.android.compose.GoogleMap

@Composable
fun BasicMapScreen(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        var isMapLoaded by remember{ mutableStateOf(false)}

        Box(Modifier.fillMaxSize()) {
            GoogleMap(
                onMapLoaded = {isMapLoaded = true}
            ){

            }

            if(!isMapLoaded){
                AnimatedVisibility(
                    modifier = Modifier.matchParentSize(),
                    visible = !isMapLoaded,
                    enter = EnterTransition.None,
                    exit = fadeOut()
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .background(MaterialTheme.colors.background)
                            .wrapContentSize()
                    )
                }
            }
        }
    }
}
