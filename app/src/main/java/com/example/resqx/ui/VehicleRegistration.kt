package com.example.resqx.ui

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.example.resqx.R

@Composable
fun VehicleRegistration(navHostController: NavHostController){
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(R.drawable.vehicleregistration, imageLoader),
            contentDescription = null,
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillBounds
        )
        Text(text = "Your Safety, One Step Away",
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            )
        Text(
            text = "Register your vehicle that provides instant access to emergency contacts and medical details—ensuring faster help when it matters most.",
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            fontSize = 16.sp,
            )

        Text(text = "\uD83D\uDCCC How It Helps You:",
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
           )

        Text(text = "✔\uFE0F Quick access to vehicle & owner details in emergencies.",
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            fontSize = 15.sp,
        )

        Text(text = "✔\uFE0F Notify your emergency contacts instantly.",
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            fontSize = 15.sp,
        )

        Text(text = "✔\uFE0F Store critical medical information for first responders.",
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            fontSize = 15.sp,
        )
        Spacer(modifier=androidx.compose.ui.Modifier.fillMaxWidth().height(10.dp))

        Text(text = "\uD83D\uDE80 Ready to Secure Your Ride?",
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Button(onClick = {
            navHostController.navigate(ResQXAppScreen.VehicleRegis2.name)
        },
            colors = ButtonDefaults.buttonColors(
                Color.Black
            )) {
            Text(text = "Continue to Registration")
        }
    }
}