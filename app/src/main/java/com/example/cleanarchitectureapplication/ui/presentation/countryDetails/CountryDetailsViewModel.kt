package com.example.cleanarchitectureapplication.ui.presentation.countryDetails

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitectureapplication.R
import com.example.cleanarchitectureapplication.domain.model.Country
import com.example.cleanarchitectureapplication.domain.model.CountryPreview
import com.example.cleanarchitectureapplication.domain.usecases.GetBorderCountriesUseCase
import com.example.cleanarchitectureapplication.domain.usecases.GetCountryDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryDetailsViewModel @Inject constructor(
    private val getCountryDetailsUseCase: GetCountryDetailsUseCase,
    private val getBorderCountriesUseCase: GetBorderCountriesUseCase,
    savedStateHandle: SavedStateHandle,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val countryId: String = savedStateHandle["countryId"] ?: throw IllegalArgumentException(
        context.getString(R.string.country_id_required)
    )

    private val _countryDetailState = MutableStateFlow<CountryDetailState>(
        CountryDetailState.Loading
    )
    val countryDetailState: StateFlow<CountryDetailState> = _countryDetailState.asStateFlow()

    init {
        fetchCountryDetails(countryId)
    }

    private fun fetchCountryDetails(id: String) {
        viewModelScope.launch {
            _countryDetailState.value = CountryDetailState.Loading
            try {
                val country = getCountryDetailsUseCase(id)
                if (country != null) {
                    val borderCountries = fetchBorderCountries(country.borders)
                    _countryDetailState.value = CountryDetailState.Success(country, borderCountries)
                } else {
                    _countryDetailState.value = CountryDetailState.Error(
                        context.getString(R.string.country_not_found)
                    )
                }
            } catch (e: Exception) {
                _countryDetailState.value =
                    CountryDetailState.Error(
                        context.getString(R.string.failed_to_load_country_details)
                    )
            }
        }
    }

    private suspend fun fetchBorderCountries(borders: String?): List<CountryPreview> {
        return getBorderCountriesUseCase(borders)
    }

    sealed class CountryDetailState {
        data object Loading : CountryDetailState()
        data class Success(
            val country: Country,
            val borderCountries: List<CountryPreview>
        ) : CountryDetailState()
        data class Error(val message: String) : CountryDetailState()
    }
}