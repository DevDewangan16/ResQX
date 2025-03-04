package com.example.resqx.ui.theme

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun ResQXApp(
    resQXViewModel: ResQXViewModel= viewModel(),
    navHostController: NavHostController= rememberNavController())
{
    LoginScreen(resQXViewModel = resQXViewModel)
}