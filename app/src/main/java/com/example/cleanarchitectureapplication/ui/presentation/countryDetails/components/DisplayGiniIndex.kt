package com.example.cleanarchitectureapplication.ui.presentation.countryDetails.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.cleanarchitectureapplication.R

@Composable
fun DisplayGiniIndex(giniData: String?){
    val context = LocalContext.current

    giniData?.let { it ->
        val parts = it.split(":").map {
            it.trim()
        }

        if (parts.size == 2) {
            val year = parts[0]
            val index = parts[1]

            TextItems(title = context.getString(R.string.gini_index_year, year), subtitle = index)
        } else {
            TextItems(
                title = context.getString(R.string.gini_index_unknown_format),
                subtitle = null
            )
        }
    } ?: run {
        TextItems(title = context.getString(R.string.gini_index_not_available), subtitle = null)
    }

}