package com.example.resqx.ui

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun VehicleRegistrationScreen(resQXViewModel: ResQXViewModel){
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color(0xFFDDEEDD)
            )
    ) {
        val vehicleNo by resQXViewModel.vehicleNo.collectAsState()
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp))
        Text(
            text = " Register Your Vehicle for Emergency Assistance",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center)
        Text(text = "Add your vehicle details and emergency contacts for instant assistance.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Center)
        OutlinedTextField(
            value = vehicleNo,
            onValueChange = {
                resQXViewModel.setVehicleNo(it)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ) ,
            label = {
                Text(text = "Vehicle Number")
            },
            placeholder = {
                Text(text = "Enter your registered vehicle number")
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

        OutlinedTextField(
            value = vehicleNo,
            onValueChange = {
                resQXViewModel.setVehicleNo(it)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ) ,
            label = {
                Text(text = "Owner Name")
            },
            placeholder = {
                Text(text = " Provide the vehicle owner’s name")
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

        OutlinedTextField(
            value = vehicleNo,
            onValueChange = {
                resQXViewModel.setVehicleNo(it)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ) ,
            label = {
                Text(text = "Emergency Contact 1")
            },
            placeholder = {
                Text(text = "Add family or friends for instant alerts.")
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

        OutlinedTextField(
            value = vehicleNo,
            onValueChange = {
                resQXViewModel.setVehicleNo(it)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ) ,
            label = {
                Text(text = "Emergency Contact 2")
            },
            placeholder = {
                Text(text = "Add family or friends for instant alerts.")
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

        OutlinedTextField(
            value = vehicleNo,
            onValueChange = {
                resQXViewModel.setVehicleNo(it)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ) ,
            label = {
                Text(text = "Blood Group")
            },
            placeholder = {
                Text(text = "Enter your Blood Group")
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

        OutlinedTextField(
            value = vehicleNo,
            onValueChange = {
                resQXViewModel.setVehicleNo(it)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ) ,
            label = {
                Text(text = "Allergies")
            },
            placeholder = {
                Text(text = "Enter any drug, food, or environmental allergies")
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

        OutlinedTextField(
            value = vehicleNo,
            onValueChange = {
                resQXViewModel.setVehicleNo(it)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ) ,
            label = {
                Text(text = "Chronic Conditions")
            },
            placeholder = {
                Text(text = "Mention conditions like diabetes, epilepsy, or asthma")
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

        Button(onClick = {},
            colors = ButtonDefaults.buttonColors(
                Color.Black
            )) {
            Text(text = "Register Vehicle")
        }

    }
}