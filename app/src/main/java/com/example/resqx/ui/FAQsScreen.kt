package com.example.resqx.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FAQsScreen(){
    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        item { 
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(25.dp))
        }
        item {
            Column {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF8F3D9)
                    )) {
                    Text(text = "How do I register my vehicle?",
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold)
                    Text(text = "Go to the Vehicle Registration screen, enter your vehicle details, emergency contacts, and generate a unique QR code.",
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        fontSize = 15.sp)
                }
            }
        }
        item {
            Column {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF8F3D9)
                    )) {
                    Text(text = "How do I scan a QR code?",
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold)
                    Text(text = "Tap on 'Scan QR Code' from the home screen, align the QR within the scanner frame, and the app will automatically fetch the details.",
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        fontSize = 15.sp)
                }
            }
        }
        item {
            Column {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF8F3D9)
                    )) {
                    Text(text = "What happens when someone scans my QR code?",
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold)
                    Text(text = "The scanner will instantly access your vehicle information, emergency contacts, and medical details, ensuring quick response in emergencies.",
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        fontSize = 15.sp)
                }
            }
        }
        item {
            Column {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF8F3D9)
                    )) {
                    Text(text = "Where should I place my QR code on my vehicle?",
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold)
                    Text(text = "We recommend placing the QR code on the windshield, dashboard, or side windows where it's easily visible for scanning.",
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        fontSize = 15.sp)
                }
            }
        }
        item {
            Column {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF8F3D9)
                    )) {
                    Text(text = "Can I update my vehicle details after registration?",
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold)
                    Text(text = "Yes, you can go to 'Settings' > 'Manage Vehicle Details' to update your information.",
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        fontSize = 15.sp)
                }
            }
        }
        item {
            Column {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF8F3D9)
                    )) {
                    Text(text = "Is my data secure?",
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold)
                    Text(text = "Yes! We use encrypted storage and Firebase Authentication to protect your personal and emergency details.",
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        fontSize = 15.sp)
                }
            }
        }
        item {
            Column {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF8F3D9)
                    )) {
                    Text(text = "Who can access my information when scanning the QR code?",
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold)
                    Text(text = "Only the essential details like emergency contacts and medical information will be visible to ensure quick assistance. Sensitive data remains protected.",
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        fontSize = 15.sp)
                }
            }
        }
        item {
            Column {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF8F3D9)
                    )) {
                    Text(text = "Can I directly call emergency contacts from the app?",
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold)
                    Text(text = "Yes! After scanning a QR code, you’ll see an option to call emergency contacts instantly.",
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        fontSize = 15.sp)
                }
            }
        }
        item {
            Column {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF8F3D9)
                    )) {
                    Text(text = "Can I add multiple vehicles under one account?",
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold)
                    Text(text = "Yes, ResQX supports multiple vehicle registrations. Simply go to 'Vehicle Management' and add another vehicle",
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        fontSize = 15.sp)
                }
            }
        }
        item {
            Column {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF8F3D9)
                    )) {
                    Text(text = "My QR code is not scanning. What should I do?",
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold)
                    Text(text = "If you are unable of the QR Code scanning then you can use the Vehicle LookUp Information features in ehich you get information after entering the vehicle number.",
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        fontSize = 15.sp)
                }
            }
        }
        item {
            Column {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF8F3D9)
                    )) {
                    Text(text = "How can I contact support?",
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold)
                    Text(text ="Go to 'Settings' > 'App Info & Support' to reach out via email or chat with our support team.",
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        fontSize = 15.sp)
                }
            }
        }
        item {
            Column {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF8F3D9)
                    )) {
                    Text(text = "Can I share my vehicle details with someone?",
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold)
                    Text(text = "Yes, you can share your QR code or send vehicle details via the 'Share' option after scanning",
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        fontSize = 15.sp)
                }
            }
        }
        item {
            Column {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF8F3D9)
                    )) {
                    Text(text = "How can I reset my password?",
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold)
                    Text(text = "Go to 'Settings' > 'Account & Security' and select 'Reset Password'. A reset link will be sent to your registered email.",
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        fontSize = 15.sp)
                }
            }
        }
    }
}