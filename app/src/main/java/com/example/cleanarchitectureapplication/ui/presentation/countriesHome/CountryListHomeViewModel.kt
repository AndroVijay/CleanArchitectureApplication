package com.example.cleanarchitectureapplication.ui.presentation.countriesHome

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitectureapplication.R
import com.example.cleanarchitectureapplication.domain.model.CountryPreview
import com.example.cleanarchitectureapplication.domain.usecases.GetCountriesUseCase
import com.example.cleanarchitectureapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListHomeViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _countriesListState = MutableStateFlow<CountriesListState>(
        CountriesListState.Loading
    )
    val countriesListState: StateFlow<CountriesListState> = _countriesListState.asStateFlow()

    init {
        getCountries(false)
    }

    fun getCountries(forceFetchFromRemote: Boolean) {
        viewModelScope.launch {
            _countriesListState.value = CountriesListState.Loading
            getCountriesUseCase(forceFetchFromRemote).collect { result ->
                _countriesListState.value = when (result) {
                    is Resource.Error -> CountriesListState.Error(
                        result.message ?: context.getString(R.string.unknown_error)
                    )
                    is Resource.Success -> CountriesListState.Success(result.data.orEmpty())
                    is Resource.Loading -> CountriesListState.Loading
                }
            }
        }
    }

    sealed class CountriesListState {
        data object Loading : CountriesListState()
        data class Success(val countries: List<CountryPreview>) : CountriesListState()
        data class Error(val message: String) : CountriesListState()
    }
}