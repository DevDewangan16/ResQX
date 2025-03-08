package com.example.resqx.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.resqx.R

@Composable
fun LoginScreen(resQXViewModel: ResQXViewModel, navHostController: NavHostController){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color(0xFFDDEEDD)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(painter = painterResource(id = R.drawable.login), contentDescription = "Login Screen",
            modifier = Modifier.size(400.dp))
        Card (
            modifier = Modifier
                .fillMaxSize(),
            elevation =CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
            colors =CardDefaults.cardColors(
                containerColor = Color.White
            )
            ){
            Text(
                text = "Welcome to ResQX !",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 20.dp),
                fontSize = 30.sp,
                color=Color.Black,
                fontWeight = FontWeight.Bold)
            Text(
                text = "Instant Help, Anytime & Anywhere",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )
            Text(text = "Ensure your safety on the road with quick access to emergency contacts, medical information, and real-time assistance—just by scanning a QR code. Stay prepared for any situation!",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                fontSize = 15.sp,
                color = Color.Black
                )
            Box (modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center){
                Button(
                    onClick = {
                        navHostController.navigate(ResQXAppScreen.SignIn.name)
                    },
                    colors = ButtonDefaults.buttonColors(
                        Color.Black
                    ),
                    shape = ButtonDefaults.elevatedShape,
                    modifier = Modifier.padding(15.dp)

                ) {
                    Text(
                        text = "Continue to Login",
                        fontSize = 15.sp,
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
        }
    }
}