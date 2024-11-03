package com.example.cleanarchitectureapplication.ui.navigation.route.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cleanarchitectureapplication.R
import com.example.cleanarchitectureapplication.ui.navigation.route.Routes
import com.example.cleanarchitectureapplication.ui.navigation.route.auth.AuthViewModel
import com.example.cleanarchitectureapplication.util.viewUtil.HeadingTextSize
import com.example.cleanarchitectureapplication.util.viewUtil.NormalTextSize
import com.example.cleanarchitectureapplication.util.viewUtil.SignUpButton
import com.example.cleanarchitectureapplication.util.viewUtil.YesAccountField
import com.example.cleanarchitectureapplication.util.viewUtil.checkBoxField
import com.example.cleanarchitectureapplication.util.viewUtil.myTextField
import com.example.cleanarchitectureapplication.util.viewUtil.passwordTextField

@Composable
fun SignUpScreen(navController: NavHostController,viewModel: AuthViewModel = hiltViewModel()) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NormalTextSize(stringResource(id = R.string.hello))

            Spacer(modifier = Modifier.height(20.dp))

            HeadingTextSize(stringResource(id = R.string.create_accounts))

            Spacer(modifier = Modifier.height(25.dp))

//            val  fname = myTextField(stringResource(id = R.string.firstname), painterResource(id = R.drawable.ic_person_pin_24))
//            Spacer(modifier = Modifier.height(15.dp))
//            val  lname =myTextField(stringResource(id = R.string.lastname), painterResource(id = R.drawable.ic_person_pin_24))
//            Spacer(modifier = Modifier.height(15.dp))

            val  email = myTextField(stringResource(id = R.string.email), painterResource(id = R.drawable.ic_email_24))

            Spacer(modifier = Modifier.height(15.dp))

            val pass= passwordTextField(stringResource(id = R.string.password), painterResource(id = R.drawable.ic_lock_24))

            Spacer(modifier = Modifier.height(20.dp))


            checkBoxField(stringResource(id = R.string.terms), onTextSelected = {

            })

            Spacer(modifier = Modifier.height(15.dp))

            SignUpButton(navController = navController, email = email, pass = pass,viewModel)


            Spacer(modifier = Modifier.height(15.dp))

            YesAccountField(stringResource(id = R.string.yes_account), onTextSelected = {

                navController.navigate(Routes.SignInScreen.route)
            })

        }

    }

}