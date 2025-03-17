package com.example.resqx.ui

import android.graphics.Bitmap
import android.graphics.Color // Use android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.example.resqx.ui.data.DataBase
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter


@Composable
fun QRCodeGeneratorScreen() {
    var qrCodeBitmap by remember { mutableStateOf<Bitmap?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Display QR Code
        qrCodeBitmap?.let { bitmap ->
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "QR Code",
                modifier = Modifier.size(200.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Button to Generate QR Code
        Button(onClick = {
            val dataBase = DataBase(
                vehicleNo = "ABC123",
                ownerName = "John Doe",
                contact1 = "1234567890",
                contact2 = "0987654321",
                bloodGroup = "O+",
                allergies = "None",
                chronicCondition = "None"
            )
            val qrCodeText = dataBase.toString()
            qrCodeBitmap = generateQRCode(qrCodeText)
        }) {
            Text("Generate QR Code")
        }
    }
}

private fun generateQRCode(text: String): Bitmap? {
    val qrCodeWriter = QRCodeWriter()
    return try {
        val bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 512, 512)
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
            }
        }
        bitmap
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}