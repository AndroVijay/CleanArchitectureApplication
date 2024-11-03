package com.example.cleanarchitectureapplication.ui.navigation.route.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cleanarchitectureapplication.ui.navigation.CountriesHome
import com.example.cleanarchitectureapplication.ui.navigation.CountryDetails
import com.example.cleanarchitectureapplication.ui.navigation.route.Routes
import com.example.cleanarchitectureapplication.ui.presentation.countriesHome.CountryListHomeScreen
import com.example.cleanarchitectureapplication.ui.presentation.countryDetails.CountryDetailsScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun SharedTransitionScope.MainScreen() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination =CountriesHome) {


        composable<CountriesHome> {
            CountryListHomeScreen(
                navController = navController,
                animatedVisibilityScope = this
            )
        }

        composable<CountryDetails> {
            CountryDetailsScreen(
                navController = navController,
                animatedVisibilityScope = this
            )
        }
    }

}