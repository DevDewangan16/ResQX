package com.example.resqx.ui

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.resqx.R

@Composable
fun SignInScreen(resQXViewModel: ResQXViewModel, navHostController: NavHostController){
    val baseContext= LocalContext.current
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
        Spacer(modifier = Modifier.fillMaxWidth().height(15.dp))
        Image(painter = painterResource(id = R.drawable.signin), contentDescription =" SignUp Image",
            modifier = Modifier
                .size(350.dp),
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
                text = "Welcome Back to ResQX!",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 20.dp),
                fontSize = 25.sp,
                color= Color.Black,
                fontWeight = FontWeight.Bold)
            Text(
                text = "Sign In to Stay Connected",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )
            Text(text = "Access your account securely and get quick assistance whenever you need it",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                fontSize = 15.sp,
                color = Color.Black
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
                    Text(text = "Enter Password")
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
                    onClick = {
                        auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    navHostController.navigate(ResQXAppScreen.Home.name){
                                        popUpTo(ResQXAppScreen.SignIn.name) {
                                            inclusive = true
                                        }
                                    }
                                    val user=task.result?.user
                                    user?.let { resQXViewModel.setUser(user) }
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                                    Toast.makeText(
                                        baseContext,
                                        "Authentication failed.",
                                        Toast.LENGTH_SHORT,
                                    ).show()
                                }
                            }
                    },
                    colors = ButtonDefaults.buttonColors(
                        Color.Black
                    ),
                    shape = ButtonDefaults.elevatedShape,

                    ) {
                    Text(
                        text = "Sign In",
                        fontSize = 15.sp,
                        modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 5.dp)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Don't have an account?",
                        fontSize = 10.sp,
                        color = Color.Black,
                    )
                    Text(
                        text = "Sign Up",
                        fontSize = 10.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            navHostController.navigate(ResQXAppScreen.SignUp.name)
                        }
                    )
                }
            }

        }
    }
}

