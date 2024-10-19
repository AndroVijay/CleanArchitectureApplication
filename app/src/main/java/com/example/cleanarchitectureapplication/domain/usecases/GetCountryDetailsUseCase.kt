package com.example.cleanarchitectureapplication.domain.usecases

import com.example.cleanarchitectureapplication.domain.repository.CountryRepository
import com.example.cleanarchitectureapplication.domain.model.Country
import com.example.cleanarchitectureapplication.util.Resource
import javax.inject.Inject

class GetCountryDetailsUseCase @Inject constructor(
    private val repository: CountryRepository
) {
    suspend operator fun invoke(id: String): Country? {
        return when (val result = repository.getCountryById(id)) {
            is Resource.Success -> result.data
            else -> null
        }
    }
}