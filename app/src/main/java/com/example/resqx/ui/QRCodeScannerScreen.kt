package com.example.resqx.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
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
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            )
            ,onClick = {
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
                Text(text =" contact1= ${details.contact1}", color = androidx.compose.ui.graphics.Color.Black)
                Text(text =" contact2= ${details.contact2}", color = androidx.compose.ui.graphics.Color.Black)
                Text(text = details.bloodGroup, color = androidx.compose.ui.graphics.Color.Black)
                Text(text = details.allergies, color = androidx.compose.ui.graphics.Color.Black)
                Text(text = details.chronicCondition, color = androidx.compose.ui.graphics.Color.Black)
                Button(onClick = { makePhoneCall1(context,phoneNumber = details.contact1)},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFA7D477)
                    )
                ) {
                    Text(text = "Call Contact 1",
                        color = Color.Black)
                }
                Button(onClick = { makePhoneCall1(context,phoneNumber = details.contact2)},
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
                contact1 = extractPhoneNumber(parts[2]), // Process phone number
                contact2 = extractPhoneNumber(parts[3]), // Process phone number
                bloodGroup = parts[4],
                allergies = parts[5],
                chronicCondition = parts[6]
            )
        } else {
            null // Return null if the format is incorrect
        }
    }
}
/**
 * Extracts a valid phone number from a string (removes prefixes like "1" or "2").
 */
private fun extractPhoneNumber(rawNumber: String): String {
    // Remove all non-digit characters first
    val digitsOnly = rawNumber.replace("[^0-9]".toRegex(), "")

    // If the number starts with "1" or "2" (incorrect prefix), remove it
    return if (digitsOnly.startsWith("1") || digitsOnly.startsWith("2")) {
        digitsOnly.substring(1)
    } else {
        digitsOnly
    }
}

fun makePhoneCall1(context: Context, phoneNumber: String) {
    val digitsOnly = phoneNumber.replace("[^0-9]".toRegex(), "")

    // Example: If the number is 10 digits, assume it's Indian (+91)
    val fullNumber = if (digitsOnly.length == 10) {
        "+91$digitsOnly" // Add country code
    } else {
        digitsOnly
    }

    val callIntent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:${Uri.encode(fullNumber)}")
    }
    context.startActivity(callIntent)
}
