package com.example.cleanarchitectureapplication

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication: MultiDexApplication()