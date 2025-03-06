package com.example.resqx.ui.theme

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

enum class ResQXAppScreen(){
    Login,
    SignIn,
    SignUp
}
@Composable
fun ResQXApp(
    resQXViewModel: ResQXViewModel= viewModel(),
    navHostController: NavHostController= rememberNavController())
{
    LoginScreen(resQXViewModel = resQXViewModel,navHostController)
    NavHost(navController = navHostController, startDestination =ResQXAppScreen.Login.name ) {
        composable(route = ResQXAppScreen.Login.name){
            LoginScreen(resQXViewModel = resQXViewModel,navHostController)
        }
        composable(route = ResQXAppScreen.SignUp.name){
            SignUpScreen(resQXViewModel = resQXViewModel, navHostController = navHostController)
        }
    }
}