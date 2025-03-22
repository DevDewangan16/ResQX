package com.example.resqx.ui

import android.graphics.Bitmap
import android.graphics.Color // Use android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.resqx.ui.data.DataBase
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter


@Composable
fun QRCodeGeneratorScreen(resQXViewModel: ResQXViewModel) {
    var qrCodeBitmap by remember { mutableStateOf<Bitmap?>(null) }

    val vehicleDisplay by resQXViewModel.vehicleDisplay.collectAsState()
    val vehicleDetails by resQXViewModel.vehicleDetails.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = vehicleDisplay,
            onValueChange = {
                resQXViewModel.setVehicleDisplay(it)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            label = {
                Text(text = "Vehicle Number")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = androidx.compose.ui.graphics.Color.Black,
                focusedBorderColor = androidx.compose.ui.graphics.Color.Black,
                focusedTextColor = androidx.compose.ui.graphics.Color.Black,
                unfocusedTextColor = androidx.compose.ui.graphics.Color.Black,
                unfocusedLabelColor = androidx.compose.ui.graphics.Color.Black,
                focusedLabelColor = androidx.compose.ui.graphics.Color.Black,
                focusedContainerColor = androidx.compose.ui.graphics.Color.White,
                unfocusedContainerColor = androidx.compose.ui.graphics.Color.White
            )
        )

        Button(onClick = {
            resQXViewModel.fetchVehicleDetails(vehicleDisplay)
            vehicleDetails?.let { details ->
                val dataBase = DataBase(
                vehicleNo = details.vehicleNo,
                ownerName = details.ownerName,
                contact1 = details.contact1,
                contact2 = details.contact2,
                bloodGroup = details.bloodGroup,
                allergies = details.allergies,
                chronicCondition = details.chronicCondition
            )
            val qrCodeText = dataBase.toString()
            qrCodeBitmap = generateQRCode(qrCodeText)

            }
        },
            colors = ButtonDefaults.buttonColors(
                androidx.compose.ui.graphics.Color.Black
            )) {
            Text(text = "Generate QR Code")
        }

        // Display QR Code
        qrCodeBitmap?.let { bitmap ->
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "QR Code",
                modifier = Modifier.size(200.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))
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