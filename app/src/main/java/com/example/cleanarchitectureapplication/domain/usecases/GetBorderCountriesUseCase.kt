package com.example.cleanarchitectureapplication.domain.usecases

import com.example.cleanarchitectureapplication.domain.repository.CountryRepository
import com.example.cleanarchitectureapplication.domain.model.CountryPreview
import com.example.cleanarchitectureapplication.util.Resource
import javax.inject.Inject

class GetBorderCountriesUseCase @Inject constructor(
    private val repository: CountryRepository
) {
    suspend operator fun invoke(borders: String?): List<CountryPreview> {
        return if (borders.isNullOrEmpty()) {
            emptyList()
        } else {
            val borderList = borders.split(",").map { it.trim() }
            when (val result = repository.getCountriesByFifaCodes(borderList)) {
                is Resource.Success -> result.data.orEmpty()
                else -> emptyList()
            }
        }
    }
}
