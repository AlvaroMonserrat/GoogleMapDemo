package com.rrat.googlemapdemo.home

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rrat.googlemapdemo.basicmap.BasicActivity
import com.rrat.googlemapdemo.MapsActivity
import com.rrat.googlemapdemo.R
import com.rrat.googlemapdemo.ui.theme.GoogleMapDemoTheme

@Composable
fun HomeScreen(){

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val context = LocalContext.current
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier.padding(16.dp),
                onClick = {
                    context.startActivity(Intent(context, BasicActivity::class.java))
                }
            ) {
                Text(stringResource(R.string.basic_map))
            }

            Button(
                modifier = Modifier.padding(16.dp),
                onClick = {
                    context.startActivity(Intent(context, MapsActivity::class.java))
                }
            ) {
                Text(stringResource(R.string.course_map))
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    GoogleMapDemoTheme {
        HomeScreen()
    }
}