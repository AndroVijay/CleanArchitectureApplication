package com.example.cleanarchitectureapplication.ui.navigation

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cleanarchitectureapplication.ui.navigation.route.Routes
import com.example.cleanarchitectureapplication.ui.navigation.route.auth.AuthViewModel
import com.example.cleanarchitectureapplication.ui.navigation.route.main.MainScreen
import com.example.cleanarchitectureapplication.ui.navigation.route.signin.SignInScreen
import com.example.cleanarchitectureapplication.ui.navigation.route.signup.SignUpScreen


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun Navigation(viewModel: AuthViewModel = hiltViewModel()) {
    SharedTransitionLayout {
        val navHostController = rememberNavController()
        val isLoggedIn = viewModel.isUserLoggedIn()
        val authResult by viewModel.authResult.collectAsState()
        val context = LocalContext.current


        NavHost(
            navController = navHostController,
            startDestination = Routes.SignInScreen.route
        ) {

          composable(Routes.SignInScreen.route) {

              if (isLoggedIn) {
                  MainScreen()
              }else{
                  SignInScreen(navController = navHostController)
              }

          }

            composable(Routes.SignupScreen.route) {

                SignUpScreen(navController = navHostController)
            }


            composable(Routes.HomeScreen.route) {

                MainScreen()
            }



        }
    }
}