package com.example.cleanarchitectureapplication.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class CountryPreview(
    val id: String,
    val commonName: String,
    val capital: String?,
    val population: Long,
    val pngFlagUrl: String
)
