package com.example.cleanarchitectureapplication.domain.repository

import com.example.cleanarchitectureapplication.domain.model.Country
import com.example.cleanarchitectureapplication.domain.model.CountryPreview
import com.example.cleanarchitectureapplication.util.Resource

interface CountryRepository {
    suspend fun getAllCountriesPreviews(forceFetchFromRemote: Boolean): Resource<List<CountryPreview>>
    suspend fun getCountryById(id: String): Resource<Country>
    suspend fun getCountriesByFifaCodes(fifaCodes: List<String>): Resource<List<CountryPreview>>
    suspend fun getLocalCountries(): Resource<List<CountryPreview>>
    suspend fun fetchRemoteCountries(): Resource<List<CountryPreview>>
}
