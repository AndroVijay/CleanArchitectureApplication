package com.example.cleanarchitectureapplication.ui.presentation.countryDetails.components

import android.content.Context
import androidx.compose.runtime.Composable
import com.example.cleanarchitectureapplication.R
import java.text.NumberFormat
import java.util.Locale


fun formatNumber(input: String, context: Context): String {
    return try {
        val number = input.toLong()
        val formatter = NumberFormat.getNumberInstance(Locale.GERMANY)
        formatter.format(number)
    } catch (e: NumberFormatException) {
        context.getString(R.string.invalid_number)
    }
}
