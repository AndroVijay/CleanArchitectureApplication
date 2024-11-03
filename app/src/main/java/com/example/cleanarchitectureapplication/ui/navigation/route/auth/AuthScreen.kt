package com.example.cleanarchitectureapplication.ui.navigation.route.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController


//@Composable
//fun AuthScreen(navController: NavHostController, viewModel: AuthViewModel = hiltViewModel()){
//
//    val authState by viewModel.authState.collectAsState()
//
//    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
//        when (authState) {
//            null -> {
//                LoginForm(
//                    onLogin = { email, password -> viewModel.signIn(email, password) },
//                    onSignUp = { email, password -> viewModel.signUp(email, password) }
//                )
//            }
//            else -> {
//                Text("Welcome, ${authState!!.email}")
//                Button(onClick = { viewModel.signOut() }) {
//                    Text("Sign Out")
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun LoginForm(
//    onLogin: (String, String) -> Unit,
//    onSignUp: (String, String) -> Unit
//) {
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//
//    Column(modifier = Modifier.fillMaxWidth()) {
//        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
//        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") })
//        Button(onClick = { onLogin(email, password) }) { Text("Sign In") }
//        Button(onClick = { onSignUp(email, password) }) { Text("Sign Up") }
//    }
//}
//
