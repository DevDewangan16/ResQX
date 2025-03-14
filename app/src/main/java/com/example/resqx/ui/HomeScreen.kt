package com.example.resqx.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.resqx.R

@Composable
fun HomeScreen(navHostController: NavHostController){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(300.dp),
        contentPadding = PaddingValues(vertical = 30.dp),
        modifier = Modifier.background(color = Color(0xFF504B38)),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalArrangement =Arrangement.spacedBy(5.dp),) {
        item {
            Text(
                text = "Welcome to ResQX",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                fontSize = 30.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
        item {
            Text(
                text = "Your Smart Vehicle Safety Companion!",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
        item {
            Card(
                modifier = Modifier
                    .size(width = 300.dp, height = 300.dp)
                    .padding(start = 10.dp, end = 10.dp)
                    .clickable {
                        navHostController.navigate(ResQXAppScreen.VehicleRegis1.name)
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFEBE5C2)
                )
            ) {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.a),
                            contentDescription = "",
                            modifier = Modifier.size(150.dp),
                            contentScale = ContentScale.FillBounds
                        )
                        Text(
                            text="\uD83D\uDE97 Vehicle Registration",
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Easily register your vehicle and add emergency contacts for instant accessibility.",
                            fontSize = 15.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )

                    }
                }
            }
        }
        item {
            Card(
                modifier = Modifier
                    .size(width = 300.dp, height = 300.dp)
                    .padding(start = 10.dp, end = 10.dp)
                    .clickable {
                        navHostController.navigate(ResQXAppScreen.VehicleInfo1.name)
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFEBE5C2)
                )
            ) {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.b),
                            contentDescription = "",
                            modifier = Modifier.size(150.dp),
                            contentScale = ContentScale.FillBounds
                        )
                        Text(
//                            text = "\uD83D\uDCC4 Summarize Documents & Notes",
                            text="\uD83D\uDD22 Vehicle Info Lookup",
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Retrieve essential details about a registered vehicle using its number.",
                            fontSize = 15.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )

                    }
                }
            }
        }
        item {
            Card(
                modifier = Modifier
                    .size(width = 300.dp, height = 300.dp)
                    .padding(start = 10.dp, end = 10.dp)
                    .clickable {
                        navHostController.navigate(ResQXAppScreen.QRDisplay1.name)
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFEBE5C2)
                )
            ) {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.c),
                            contentDescription = "",
                            modifier = Modifier.size(150.dp),
                            contentScale = ContentScale.FillBounds
                        )
                        Text(
//                            text = "\uD83D\uDCC4 Summarize Documents & Notes",
                            text="\uD83C\uDD98 QR Code Display",
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "View, download, and share your unique vehicle QR code for quick roadside assistance.",
                            fontSize = 15.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )

                    }
                }
            }
        }
        item {
            Card(
                modifier = Modifier
                    .size(width = 300.dp, height = 300.dp)
                    .padding(start = 10.dp, end = 10.dp)
                    .clickable {
                        navHostController.navigate(ResQXAppScreen.QRScanner1.name)
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFEBE5C2)
                )
            ) {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.d),
                            contentDescription = "",
                            modifier = Modifier.size(150.dp),
                            contentScale = ContentScale.FillBounds
                        )
                        Text(
                            text="\uD83D\uDCF8 QR Code Scanner",
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Scan a vehicle's QR code to access emergency contact and medical details instantly.\n" +
                                    "\n",
                            fontSize = 15.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )

                    }
                }
            }
        }
        item {
            Card(
                modifier = Modifier
                    .size(width = 300.dp, height = 300.dp)
                    .padding(start = 10.dp, end = 10.dp)
                    .clickable {
                        navHostController.navigate(ResQXAppScreen.Chatbot.name)
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFEBE5C2)
                )
            ) {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.e),
                            contentDescription = "",
                            modifier = Modifier.size(150.dp),
                            contentScale = ContentScale.FillBounds
                        )
                        Text(
                            text="\uD83E\uDD16 Safety Chatbot",
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Get real-time answers to vehicle safety rules and emergency procedures.\n" +
                                    "\n",
                            fontSize = 15.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )

                    }
                }
            }
        }
        item {
            Card(
                modifier = Modifier
                    .size(width = 300.dp, height = 300.dp)
                    .padding(start = 10.dp, end = 10.dp)
                    .clickable {
                        navHostController.navigate(ResQXAppScreen.History.name)
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFEBE5C2)
                )
            ) {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.f),
                            contentDescription = "",
                            modifier = Modifier.size(150.dp),
                            contentScale = ContentScale.FillBounds
                        )
                        Text(
                            text="\uD83D\uDCDC History Log",
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Track all your past queries and AI-generated responses for future reference.\n" +
                                    "\n",
                            fontSize = 15.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )

                    }
                }
            }
        }
        item {
            Card(
                modifier = Modifier
                    .size(width = 300.dp, height = 300.dp)
                    .padding(start = 10.dp, end = 10.dp)
                    .clickable {
                        navHostController.navigate(ResQXAppScreen.Save.name)
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFEBE5C2)
                )
            ) {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.g),
                            contentDescription = "",
                            modifier = Modifier.size(150.dp),
                            contentScale = ContentScale.FillBounds
                        )
                        Text(
                            text="\uD83D\uDCCC Saved Records",
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Store and access previously generated responses for quick retrieval anytime.\n" +
                                    "\n",
                            fontSize = 15.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )

                    }
                }
            }
        }
        item {
            Card(
                modifier = Modifier
                    .size(width = 300.dp, height = 300.dp)
                    .padding(start = 10.dp, end = 10.dp)
                    .clickable {
                        navHostController.navigate(ResQXAppScreen.Settings.name)
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFEBE5C2)
                )
            ) {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.h),
                            contentDescription = "",
                            modifier = Modifier.size(150.dp),
                            contentScale = ContentScale.FillBounds
                        )
                        Text(
                            text="⚙\uFE0F Settings",
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Manage preferences, security options, and notification settings.\n" +
                                    "\n",
                            fontSize = 15.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )

                    }
                }
            }
        }
        item {
            Card(
                modifier = Modifier
                    .size(width = 300.dp, height = 300.dp)
                    .padding(start = 10.dp, end = 10.dp)
                    .clickable {
                        navHostController.navigate(ResQXAppScreen.FAQs.name)
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFEBE5C2)
                )
            ) {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.faqs),
                            contentDescription = "",
                            modifier = Modifier.size(150.dp),
                            contentScale = ContentScale.FillBounds
                        )
                        Text(
                            text="❓ FAQs",
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Find quick solutions to your queries about ResQX, from QR code usage to security settings",
                            fontSize = 15.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )

                    }
                }
            }
        }
    }
}