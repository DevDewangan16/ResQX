package com.example.resqx.ui

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.example.resqx.R

@Composable
fun QRDisplay(){
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
            painter = rememberAsyncImagePainter(R.drawable.qrdisplay, imageLoader),
            contentDescription = null,
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillBounds
        )
        Text(text = "Your Emergency QR Code, Always Ready",
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "Generate and display a unique QR code linked to your vehicle details. In case of an emergency, anyone can scan it to instantly access your essential information.",
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

        Text(text = "✔\uFE0F Ensures quick access to emergency contacts and medical details.",
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            fontSize = 15.sp,
        )

        Text(text = "✔\uFE0F Helps first responders take immediate action.",
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            fontSize = 15.sp,
        )

        Text(text = "✔\uFE0F Secure and easily shareable QR code for roadside assistance.",
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            fontSize = 15.sp,
        )

        Text(text = "✔\uFE0F Works even without an internet connection.",
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            fontSize = 15.sp,
        )

        Text(text = "\uD83D\uDE80 Continue to QR Code",
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