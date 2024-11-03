package com.example.cleanarchitectureapplication.ui.navigation.route.auth.model

data class User(

    val isLogin : Boolean = false,
    val firstName: String = "",
    val lastName: String = "",
    val emailAddress: String = "",
    val password: String = ""
)
