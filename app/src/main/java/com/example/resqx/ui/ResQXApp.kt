package com.example.resqx.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
    Save,
    Settings
}

val auth= FirebaseAuth.getInstance()

@Composable
fun ResQXApp(
    resQXViewModel: ResQXViewModel = viewModel(),
    navHostController: NavHostController= rememberNavController())
{
    val isvisible by resQXViewModel.isvisible.collectAsState()
    val user by resQXViewModel.user.collectAsState()
    val logoutClicked by resQXViewModel.logoutClicked.collectAsState()//used to manage the the manage the logout or alert screen

    
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

    if (isvisible){
        SplashScreen()
    }
    else{
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
                QRDisplay(navHostController)
            }
            composable(route = ResQXAppScreen.QRDisplay2.name){
                QRCodeGeneratorScreen()
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
            composable(route = ResQXAppScreen.Settings.name){
                SettingScreen(resQXViewModel = resQXViewModel, navHostController = navHostController)
            }
        }
        if (logoutClicked){
            AlertCheck(onYesButtonPressed = {
                auth.signOut()
                resQXViewModel.clearData()
                resQXViewModel.setLogoutStatus(false)
                navHostController.navigate(ResQXAppScreen.Login.name) {
                    popUpTo(ResQXAppScreen.Login.name) {
                        inclusive = true
                    }
                }
            },
                onNoButtonPressed = {
                    resQXViewModel.setLogoutStatus(false)
                }
            )
        }
    }
}

@Composable
fun AlertCheck(
    onYesButtonPressed:()->Unit,
    onNoButtonPressed:()->Unit

){
    AlertDialog(
        title = {
            Text(text = "Logout?", fontWeight = FontWeight.Bold)
        },
        containerColor = Color.White,
        text = {
            Text(text = "Are you sure you want to Logout")
        },
        confirmButton = {
            TextButton(onClick = {
                onYesButtonPressed()
            }) {
                Text(text = "Yes")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onNoButtonPressed()
            }) {
                Text(text = "No")
            }
        },
        onDismissRequest = {
            onNoButtonPressed()
        }
    )
}
