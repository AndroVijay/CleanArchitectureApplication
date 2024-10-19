package com.example.cleanarchitectureapplication.domain.usecases

import com.example.cleanarchitectureapplication.domain.repository.CountryRepository
import com.example.cleanarchitectureapplication.domain.model.CountryPreview
import com.example.cleanarchitectureapplication.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(
    private val repository: CountryRepository
) {
    operator fun invoke(forceFetchFromRemote: Boolean): Flow<Resource<List<CountryPreview>>> = flow {
        emit(Resource.Loading())
        val result = repository.getAllCountriesPreviews(forceFetchFromRemote)
        emit(result)
    }.flowOn(Dispatchers.IO)
}
