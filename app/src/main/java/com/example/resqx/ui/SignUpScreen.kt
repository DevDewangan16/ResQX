package com.example.resqx.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.resqx.R

@Composable
fun SignUpScreen(resQXViewModel: ResQXViewModel, navHostController: NavHostController){
    val name by resQXViewModel.name.collectAsState()
    val email by resQXViewModel.email.collectAsState()
    val password by resQXViewModel.password.collectAsState()
    val confirmPass by resQXViewModel.confirmPass.collectAsState()
    Column(
        modifier= Modifier
            .fillMaxSize()
            .background(
                color = Color(0xFFDDEEDD)
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,){
        Image(painter = painterResource(id = R.drawable.signup), contentDescription =" SignUp Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.FillWidth)
        Card (
            modifier = Modifier.fillMaxSize(),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
            ){
            Text(
                text = "Create Your ResQX Account",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 20.dp),
                fontSize = 25.sp,
                color=androidx.compose.ui.graphics.Color.Black,
                fontWeight = FontWeight.Bold)
            Text(
                text = "Join ResQX for a Safer Journey",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                fontSize = 20.sp,
                color = androidx.compose.ui.graphics.Color.Black,
                fontWeight = FontWeight.SemiBold
            )
            Text(text = "Sign up to access smart emergency assistance, secure your details, and stay connected when it matters most.",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                fontSize = 15.sp,
                color = androidx.compose.ui.graphics.Color.Black
            )
            OutlinedTextField(
                value = name,
                onValueChange = {
                    resQXViewModel.setName(it)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ) ,
                label = {
                    Text(text = "FullName")
                },
                placeholder = {
                    Text(text = "Enter your Full Name")
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
                    focusedLabelColor = Color.Black
                )
            )
            OutlinedTextField(
                value = email,
                onValueChange = {
                    resQXViewModel.setEmail(it)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ) ,
                label = {
                    Text(text = "Email")
                },
                placeholder = {
                    Text(text = "Enter your email address")
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
                    focusedLabelColor = Color.Black
                )
            )
            OutlinedTextField(
                value = password,
                onValueChange = {
                    resQXViewModel.setPassword(it)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ) ,
                label = {
                    Text(text = "Password")
                },
                placeholder = {
                    Text(text = "Create Password")
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
                    focusedLabelColor = Color.Black
                )
            )
            OutlinedTextField(
                value = confirmPass,
                onValueChange = {
                    resQXViewModel.setConfirmPass(it)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ) ,
                label = {
                    Text(text = "Confirm Password")
                },
                placeholder = {
                    Text(text = "Re-enter the password")
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
                    focusedLabelColor = Color.Black
                )
            )
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        Color.Black
                    ),
                    shape = ButtonDefaults.elevatedShape,

                    ) {
                    Text(
                        text = "Sign Up",
                        fontSize = 15.sp,
                        modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 5.dp)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Already have an account?",
                        fontSize = 10.sp,
                        color = Color.Black,
                    )
                    Text(
                        text = "Sign In",
                        fontSize = 10.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            navHostController.navigate(ResQXAppScreen.SignIn.name)
                        }
                    )
                }
            }

        }
    }
}