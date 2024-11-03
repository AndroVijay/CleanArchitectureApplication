package com.example.cleanarchitectureapplication.ui.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cleanarchitectureapplication.ui.navigation.route.Routes
import com.example.cleanarchitectureapplication.ui.navigation.route.auth.AuthViewModel
import com.example.cleanarchitectureapplication.ui.navigation.route.main.MainScreen
import com.example.cleanarchitectureapplication.ui.navigation.route.signin.SignInScreen
import com.example.cleanarchitectureapplication.ui.navigation.route.signup.SignUpScreen


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun Navigation(viewModel: AuthViewModel = hiltViewModel()) {
    SharedTransitionLayout {
        val navHostController = rememberNavController()
        val isLoggedIn = viewModel.isUserLoggedIn()

        NavHost(
            navController = navHostController,
            startDestination = Routes.SignInScreen.route
        ) {

          composable(Routes.SignInScreen.route) {

              if (!isLoggedIn) {
                  viewModel.signOut()
                  SignInScreen(navController = navHostController)
              }else{
                  MainScreen()
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