package com.example.cleanarchitectureapplication.domain.dataSource

import com.example.cleanarchitectureapplication.data.local.CountryEntity
import com.example.cleanarchitectureapplication.data.local.CountryEntityPreview
import com.example.cleanarchitectureapplication.domain.model.CountryPreview

interface CountryDataSource {
    suspend fun getAllCountriesPreview(): List<CountryEntityPreview>
    suspend fun upsertCountryList(countryList: List<CountryEntity>)
    suspend fun getCountryById(countryId: String): CountryEntity?
    suspend fun getCountriesByFifaCodes(cca3Codes: List<String>): List<CountryPreview>
}
