package com.example.resqx.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth

enum class ResQXAppScreen(){
    Login,
    SignIn,
    SignUp,
    Home,
    VehicleRegis1,
    VehicleRegis2,
    VehicleInfo1,
    VehicleInfo2
}

val auth= FirebaseAuth.getInstance()

@Composable
fun ResQXApp(
    resQXViewModel: ResQXViewModel = viewModel(),
    navHostController: NavHostController= rememberNavController())
{
    val user by resQXViewModel.user.collectAsState()
    auth.currentUser?.let { resQXViewModel.setUser(it) }
    
    VehicleInfoScreen(resQXViewModel = resQXViewModel)

//    NavHost(navController = navHostController, startDestination = ResQXAppScreen.Login.name ) {
//        composable(route = ResQXAppScreen.Login.name){
//            LoginScreen(resQXViewModel = resQXViewModel,navHostController)
//        }
//        composable(route = ResQXAppScreen.SignUp.name){
//            SignUpScreen(resQXViewModel = resQXViewModel, navHostController = navHostController)
//        }
//        composable(route = ResQXAppScreen.SignIn.name){
//            SignInScreen(resQXViewModel = resQXViewModel, navHostController = navHostController)
//        }
//        composable(route =ResQXAppScreen.Home.name){
//            HomeScreen(navHostController)
//        }
//        composable(route = ResQXAppScreen.VehicleRegis1.name){
//            VehicleRegistration(navHostController = navHostController)
//        }
//        composable(route = ResQXAppScreen.VehicleRegis2.name){
//            VehicleRegistrationScreen(resQXViewModel = resQXViewModel)
//        }
//        composable(route = ResQXAppScreen.VehicleInfo1.name){
//            VehicleInfo(navHostController = navHostController)
//        }
//    }
}