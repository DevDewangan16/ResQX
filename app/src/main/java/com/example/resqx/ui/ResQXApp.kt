package com.example.resqx.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.resqx.ui.data.DataBase
import com.google.firebase.auth.FirebaseAuth

enum class ResQXAppScreen(){
    Login,
    SignIn,
    SignUp,
    Home,
    VehicleRegis1,
    VehicleRegis2,
    VehicleInfo1,
    VehicleInfo2,
    QRDisplay1,
    QRDisplay2,
    QRScanner1,
    QRScanner2,
    Chatbot,
    History,
    FAQs,
    Save
}

val auth= FirebaseAuth.getInstance()

@Composable
fun ResQXApp(
    resQXViewModel: ResQXViewModel = viewModel(),
    navHostController: NavHostController= rememberNavController())
{
    val user by resQXViewModel.user.collectAsState()
    auth.currentUser?.let { resQXViewModel.setUser(it) }
    val backStackEntry by navHostController.currentBackStackEntryAsState()//used to control the back buttton navigation
    val currentScreen =ResQXAppScreen.valueOf(
        backStackEntry?.destination?.route?:ResQXAppScreen.Home.name
    )
    val startDestination = if (user == null) {
        ResQXAppScreen.Login.name
    } else {
        ResQXAppScreen.Home.name
    }

    NavHost(navController = navHostController, startDestination = startDestination ) {
        composable(route = ResQXAppScreen.Login.name){
            LoginScreen(resQXViewModel = resQXViewModel,navHostController)
        }
        composable(route = ResQXAppScreen.SignUp.name){
            SignUpScreen(resQXViewModel = resQXViewModel, navHostController = navHostController)
        }
        composable(route = ResQXAppScreen.SignIn.name){
            SignInScreen(resQXViewModel = resQXViewModel, navHostController = navHostController)
        }
        composable(route =ResQXAppScreen.Home.name){
            HomeScreen(navHostController)
        }
        composable(route = ResQXAppScreen.VehicleRegis1.name){
            VehicleRegistration(navHostController = navHostController)
        }
        composable(route = ResQXAppScreen.VehicleRegis2.name){
            VehicleRegistrationScreen(resQXViewModel = resQXViewModel)
        }
        composable(route = ResQXAppScreen.VehicleInfo1.name){
            VehicleInfo(navHostController = navHostController)
        }
        composable(route = ResQXAppScreen.VehicleInfo2.name){
            VehicleInfoScreen(resQXViewModel = resQXViewModel)
        }
        composable(route = ResQXAppScreen.QRScanner1.name){
            QRScanner(navHostController = navHostController)
        }
        composable(route = ResQXAppScreen.QRDisplay1.name){
            QRDisplay()
        }
        composable(route = ResQXAppScreen.Chatbot.name){
            ChatbotScreen(resQXViewModel = resQXViewModel)
        }
        composable(route = ResQXAppScreen.History.name){
            HistoryScreen(resQXViewModel = resQXViewModel, navHostController = navHostController)
        }
        composable(route = ResQXAppScreen.FAQs.name){
            FAQsScreen()
        }
        composable(route = ResQXAppScreen.Save.name){
            SaveScreen(resQXViewModel = resQXViewModel, navController = navHostController)
        }
    }
}