package com.example.resqx.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun VehicleInfoScreen(resQXViewModel: ResQXViewModel) {
    val context = LocalContext.current
    val vehicleInfo by resQXViewModel.vehicleInfo.collectAsState()
    val vehicleDetails by resQXViewModel.vehicleDetails.collectAsState()

    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White
            )
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
        )
        Text(
            text = "Instant Vehicle Info Lookup",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Enter a vehicle number to access details instantly",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
        OutlinedTextField(
            value = vehicleInfo,
            onValueChange = {
                resQXViewModel.setVehicleInfo(it)
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
                unfocusedBorderColor = Color.Black,
                focusedBorderColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                focusedLabelColor = Color.Black,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )

        Button(onClick = {
            resQXViewModel.fetchVehicleDetails(vehicleInfo)
            resQXViewModel.setVehicleInfo("")
        },
            colors = ButtonDefaults.buttonColors(
                Color.Black
            )) {
            Text(text = "Search")
        }

        vehicleDetails?.let { details ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Owner Name: ${details.ownerName}", color = Color.Black)
                Text(text = "Contact 1: ${details.contact1}", color = Color.Black)
                Text(text = "Contact 2: ${details.contact2}", color = Color.Black)
                Text(text = "Blood Group: ${details.bloodGroup}", color = Color.Black)
                Text(text = "Allergies: ${details.allergies}", color = Color.Black)
                Text(text = "Chronic Condition: ${details.chronicCondition}", color = Color.Black)
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
        } ?: run {
            if (vehicleInfo.isNotEmpty()) {
                Text(text = "No details found for the entered vehicle number.", color = Color.Red)
            }
        }
    }
}
fun makePhoneCall(context: Context, phoneNumber: String) {
    val callIntent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    context.startActivity(callIntent)
}