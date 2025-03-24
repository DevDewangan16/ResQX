package com.example.resqx.ui

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.resqx.ui.data.DataBase
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import com.journeyapps.barcodescanner.ScanIntentResult


@Composable
fun QRCodeScannerScreen() {
    var scannedResult by remember { mutableStateOf<String?>(null) }
    var vehicleDetails by remember { mutableStateOf<DataBase?>(null) }
    val context = LocalContext.current

    // Launch the QR Code Scanner
    val scanLauncher = rememberLauncherForActivityResult(
        contract = ScanContract(),
        onResult = { result: ScanIntentResult ->
            if (result.contents != null) {
                scannedResult = result.contents
                Log.d("QRCodeScanner", "Scanned Result: $scannedResult") // Log the scanned result
                vehicleDetails = parseScannedResult(scannedResult)
            } else {
                scannedResult = "Scan cancelled"
            }
        }
    )

    // Check for camera permissions
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                // Permission granted, start scanning
                val options = ScanOptions().apply {
                    setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                    setPrompt("Scan a QR Code")
                    setCameraId(0)
                    setBeepEnabled(false)
                    setBarcodeImageEnabled(true)
                }
                scanLauncher.launch(options)
            } else {
                // Permission denied
                scannedResult = "Camera permission denied"
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Button to Start Scanning
        Button(onClick = {
            // Check for camera permissions
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                // Permission already granted, start scanning
                val options = ScanOptions().apply {
                    setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                    setPrompt("Scan a QR Code")
                    setCameraId(0)
                    setBeepEnabled(false)
                    setBarcodeImageEnabled(true)
                }
                scanLauncher.launch(options)
            } else {
                // Request camera permission
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }) {
            Text("Scan QR Code")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display Scanned Result
        vehicleDetails?.let { details ->
            Column {
                Text(text = details.ownerName, color = androidx.compose.ui.graphics.Color.Black)
                Text(text = details.contact1, color = androidx.compose.ui.graphics.Color.Black)
                Text(text = details.contact2, color = androidx.compose.ui.graphics.Color.Black)
                Text(text = details.bloodGroup, color = androidx.compose.ui.graphics.Color.Black)
                Text(text = details.allergies, color = androidx.compose.ui.graphics.Color.Black)
                Text(text = details.chronicCondition, color = androidx.compose.ui.graphics.Color.Black)
                Button(onClick = { makePhoneCall(context,phoneNumber = details.contact1)},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFA7D477)
                    )
                ) {
                    Text(text = "Call Contact 1",
                        color = Color.Black)
                }
                Button(onClick = { makePhoneCall(context,phoneNumber = details.contact2)},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFA7D477)
                    )) {
                    Text(text = "Call Contact 2",
                        color= Color.Black)
                }
            }
        } ?: if (scannedResult != null) {
            Text(text = "Invalid QR code format", color = androidx.compose.ui.graphics.Color.Red)
        } else {
            Text(text = "No QR code scanned yet", color = androidx.compose.ui.graphics.Color.Black)
        }
    }
//    var scannedResult by remember { mutableStateOf<String?>(null) }
//    val context = LocalContext.current
//
//    // Launch the QR Code Scanner
//    val scanLauncher = rememberLauncherForActivityResult(
//        contract = ScanContract(),
//        onResult = { result: ScanIntentResult ->
//            if (result.contents != null) {
//                scannedResult = result.contents
//            } else {
//                scannedResult = "Scan cancelled"
//            }
//        }
//    )
//
//    // Check for camera permissions
//    val requestPermissionLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.RequestPermission(),
//        onResult = { isGranted ->
//            if (isGranted) {
//                // Permission granted, start scanning
//                val options = ScanOptions().apply {
//                    setDesiredBarcodeFormats(ScanOptions.QR_CODE)
//                    setPrompt("Scan a QR Code")
//                    setCameraId(0)
//                    setBeepEnabled(false)
//                    setBarcodeImageEnabled(true)
//                }
//                scanLauncher.launch(options)
//            } else {
//                // Permission denied
//                scannedResult = "Camera permission denied"
//            }
//        }
//    )
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        // Button to Start Scanning
//        Button(onClick = {
//            // Check for camera permissions
//            if (ContextCompat.checkSelfPermission(
//                    context,
//                    Manifest.permission.CAMERA
//                ) == PackageManager.PERMISSION_GRANTED
//            ) {
//                // Permission already granted, start scanning
//                val options = ScanOptions().apply {
//                    setDesiredBarcodeFormats(ScanOptions.QR_CODE)
//                    setPrompt("Scan a QR Code")
//                    setCameraId(0)
//                    setBeepEnabled(false)
//                    setBarcodeImageEnabled(true)
//                }
//                scanLauncher.launch(options)
//            } else {
//                // Request camera permission
//                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
//            }
//        }) {
//            Text("Scan QR Code")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Display Scanned Result
//        scannedResult?.let { result ->
//            Text(text = "Scanned Result: $result")
//        }
//    }
}

//Function to parse the scanned result into a DataBase object
private fun parseScannedResult(scannedResult: String?): DataBase? {
    return scannedResult?.let { result ->
        val parts = result.split(",")
        if (parts.size == 7) { // Ensure there are exactly 7 parts
            DataBase(
                vehicleNo = parts[0],
                ownerName = parts[1],
                contact1 = parts[2],
                contact2 = parts[3],
                bloodGroup = parts[4],
                allergies = parts[5],
                chronicCondition = parts[6]
            )
        } else {
            null // Return null if the format is incorrect
        }
    }
}