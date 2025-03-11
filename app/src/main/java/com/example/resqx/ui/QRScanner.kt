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
fun QRScanner(navHostController: NavHostController){
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
        verticalArrangement = Arrangement.spacedBy(7.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(R.drawable.qrsearch, imageLoader),
            contentDescription = null,
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillBounds
        )
        Text(text = "Scan & Access Emergency Details Instantly",
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "Quickly retrieve vehicle owner information, emergency contacts, and medical details by scanning the vehicle’s QR code. Ensure immediate assistance when needed.",
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            fontSize = 16.sp,
        )

        Text(text = "\uD83D\uDCCC Why Use This?",
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Text(text = "✔\uFE0F Instantly fetch vehicle details with a single scan.",
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            fontSize = 15.sp,
        )

        Text(text = "✔\uFE0F Contact emergency numbers with a single scan.",
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            fontSize = 15.sp,
        )

        Text(text = "✔\uFE0F Access critical medical info for first responders.",
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            fontSize = 15.sp,
        )

        Text(text = "✔\uFE0F Faster response in case of accidents or emergencies.",
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            fontSize = 15.sp,
        )

        Text(text = "\uD83D\uDE80 Ready to Scan?",
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Button(onClick = {},
            colors = ButtonDefaults.buttonColors(
                Color.Black
            )) {
            Text(text = "Continue to Scanner")
        }
    }
}