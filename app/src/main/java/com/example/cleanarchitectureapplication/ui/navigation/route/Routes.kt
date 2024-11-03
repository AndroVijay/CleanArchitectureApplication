package com.example.cleanarchitectureapplication.ui.navigation.route

sealed class Routes(var route:String) {

    object SignupScreen: Routes("signup")
    object SignInScreen: Routes("signin")
    object HomeScreen:Routes("home")
}